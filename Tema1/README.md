# Tema 1 – Analiză Lexicală și Sintactică (Cool Compiler)

Proiect realizat pentru cursul **CPL**, având ca obiectiv implementarea analizorului lexical și sintactic pentru limbajul **Cool**, folosind **ANTLR**.  
Această etapă reprezintă baza compilatorului și generează un **AST (Abstract Syntax Tree)** care va fi utilizat în etapele următoare (analiză semantică, generare de cod).

## Obiective
- Implementarea specificațiilor lexicale și sintactice ale limbajului Cool
- Utilizarea instrumentului **ANTLR**
- Construirea și parcurgerea arborelui de derivare și a AST-ului
- Gestionarea erorilor lexicale și sintactice

## Analiza lexicală
- Definirea regulilor lexicale în ANTLR conform manualului Cool (secțiunile 10 și 11)
- Transformări pentru șiruri de caractere:
  - Eliminarea ghilimelelor
  - Interpretarea secvențelor speciale (`\n`, `\t`, `\b`, `\f`)
  - Transformarea `\c` → `c`
- Erori lexicale tratate prin token `ERROR`:
  - String constant too long (>1024 caractere)
  - String contains null character
  - Unterminated string constant (newline non-escaped)
  - EOF in string constant
  - Unmatched `*)` în comentarii
  - EOF in comment
  - Invalid character

## Analiza sintactică
- Definirea gramaticii Cool în ANTLR
- Construirea AST-ului prin traversarea arborelui de derivare
- Nodurile AST includ:
  - `program`, `class`, `attribute`, `method`, `variable`, `literal`
  - Expresii: operatori binari/unari, atribuiri, dispatch, if, while, let, case, block
- Output: afișarea ierarhiei AST cu indentare pe 2 spații

## Testare
- Testarea analizorului lexical independent (listare tokeni)
- Testarea analizorului sintactic cu `cool.tester.Tester1.main`
- Teste disponibile în `tests/tema1/`:
  - `.cl` = programe Cool de analizat
  - `.ref` = output de referință
- Scor calculat incremental (maxim 100 puncte)

## Structura proiectului
- `cool.compiler` – modulul principal
- `cool.lexer` – analizor lexical (CoolLexer.g4)
- `cool.parser` – analizor sintactic (CoolParser.g4)
- `cool.tester` – modul de testare
- `tests/tema1` – testele automate
