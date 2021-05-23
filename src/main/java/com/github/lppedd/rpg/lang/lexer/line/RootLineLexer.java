package com.github.lppedd.rpg.lang.lexer.line;

import com.github.lppedd.rpg.lang.lexer.token.Lexeme;
import com.github.lppedd.rpg.lang.lexer.token.LineLexeme;
import com.intellij.psi.TokenType;
import org.jetbrains.annotations.NotNull;

import java.util.Queue;

/**
 * Extracts the line type and redirects it to the appropriate {@link LineLexer}.
 *
 * @author Edoardo Luppi
 */
public class RootLineLexer implements LineLexer {
  private final LineLexer controlLineLexer = new ControlLineLexer();
  private final LineLexer fileLineLexer = new FileLineLexer();
  private final LineLexer definitionLineLexer = new DefinitionLineLexer();
  private final LineLexer procedureLineLexer = new ProcedureLineLexer();
  private final LineLexer commentLineLexer = new CommentLineLexer();
  private final LineLexer unknownLineLexer = new UnknownLineLexer();

  @Override
  public void lex(@NotNull final CharSequence line, @NotNull final Queue<? super Lexeme> lexemes) {
    final var lineLength = line.length();
    assert lineLength > 0 : "The line should never be empty";

    final var hasEol = line.charAt(lineLength - 1) == '\n';
    final var strippedLine = hasEol
        ? line.subSequence(0, lineLength - 1)
        : line;

    if (strippedLine.length() > 0) {
      final var lineType = lineLength > 5
          ? Character.toUpperCase(line.charAt(5))
          : ' ';

      switch (lineType) {
        case 'H':
          controlLineLexer.lex(strippedLine, lexemes);
          break;
        case 'F':
          fileLineLexer.lex(strippedLine, lexemes);
          break;
        case 'D':
          definitionLineLexer.lex(strippedLine, lexemes);
          break;
        case 'P':
          procedureLineLexer.lex(strippedLine, lexemes);
          break;
        case '*':
          commentLineLexer.lex(strippedLine, lexemes);
          break;
        default:
          unknownLineLexer.lex(strippedLine, lexemes);
          break;
      }
    }

    if (hasEol) {
      lexemes.add(new LineLexeme(TokenType.WHITE_SPACE, lineLength - 1, lineLength));
    }
  }
}
