## Lexer for RPG fixed format

To lex a fixed format RPG source file, which has column-aware tokens,
I've chosen to write code manually instead of relying on lexer generators.
The current process involves a couple components:

- a `LineBufferReader`, that is capable of reading line by line out of the characters buffer
- a `LineLexer`, that is responsible for splitting the line read by the `LineBufferReader`
- a `TokenLexer`, that can be used by the `LineLexer` and is responsible for analysing 
  the single token and emitting one or more corresponding `Lexeme`s.
  I've written "one or more" because a token might contain multiple types of information
  (e.g. whitespaces, valid characters, bad characters) and thus it'd need to be split even more.

Given that the fixed format RPG tokens work on the single line, when the lexer advances
I'm reading the entirety of the line instead of a single token. The additional tokens get
stored inside a `Queue` and they are polled on subsequent advances.  
When the `Queue` is empty again, another line gets read. This process goes on until EOF.
