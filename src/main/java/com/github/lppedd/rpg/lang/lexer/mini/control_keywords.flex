package com.github.lppedd.rpg.lang.lexer.mini;

import java.util.ArrayDeque;
import java.util.Deque;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.lexer.FlexLexer;

import com.github.lppedd.rpg.lang.lexer.RpgTokenType;

%%

%class KeywordsRpgFlexLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode
%ignorecase

whitespace      = [ ]
whitespaces     = [ ]+

lparen          = "("
rparen          = ")"

keyword         = [a-zA-Z][a-zA-Z0-9*_]*
special_keyword = \*?[a-zA-Z][a-zA-Z0-9*_]*
bad_keyword     = [0-9]+

str_literal_begin = '
str_literal_end   = '
str_literal    = [a-zA-Z0-9 ]+

numeric_literal = [0-9]+


%xstate STRING_LITERAL
%xstate BAD_KEYWORD
%xstate PARENS

%{

  private final Deque<Integer> states = new ArrayDeque<>();

  private void yypushbegin(int state) {
    states.addFirst(yystate());
    yybegin(state);
  }

  private void yypopbegin() {
    yybegin(states.removeFirst());
  }

%}

%%

<YYINITIAL, PARENS> {
      {whitespaces} {
        return TokenType.WHITE_SPACE;
      }
}

<YYINITIAL> {
      {keyword} {
        return RpgTokenType.SPECIFICATION;
      }

      {lparen} {
        yypushbegin(PARENS);
        return RpgTokenType.LPAREN;
      }

      // The literal opening quote must have a corresponding closing one,
      // otherwise it's just a bad character
      {str_literal_begin}/.*' {
        yypushbegin(STRING_LITERAL);
      }

      [^] {
        yybegin(BAD_KEYWORD);
      }
}

<BAD_KEYWORD> {
      // The bad part of the keyword must stop when a space is found,
      // as the space act as separator between keywords
      {whitespace} {
        yypushback(yylength());
        yybegin(YYINITIAL);
        return TokenType.BAD_CHARACTER;
      }

      [^ ] {}

      <<EOF>> {
        yybegin(YYINITIAL);
        return TokenType.BAD_CHARACTER;
      }
}

<PARENS> {
      // The literal opening quote must have a corresponding closing one,
      // otherwise it's just a bad character
      {str_literal_begin}/.*' {
        yypushbegin(STRING_LITERAL);
      }

      {numeric_literal} {
              return RpgTokenType.NUMERIC_LITERAL;
            }

      {special_keyword} {
        return RpgTokenType.INNER_SPECIFICATION;
      }

      {rparen} {
        yypopbegin();
        return RpgTokenType.RPAREN;
      }

      [^] {
        return TokenType.BAD_CHARACTER;
      }

      <<EOF>> {
        yybegin(YYINITIAL);
        return TokenType.BAD_CHARACTER;
      }
}

<STRING_LITERAL> {
      {str_literal} {
        // Keep reading
      }

      {str_literal_end} {
        yypopbegin();
        return RpgTokenType.STRING_LITERAL;
      }

      <<EOF>> {
        yybegin(YYINITIAL);
        return TokenType.BAD_CHARACTER;
      }
}

<YYINITIAL, STRING_LITERAL>
      [^] {
        return TokenType.BAD_CHARACTER;
      }
