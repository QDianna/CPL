# Tema 2 – Analiză Semantică (Cool Compiler)

Proiect realizat pentru cursul **CPL**, continuând etapele compilatorului pentru limbajul **Cool**.  
Scopul este implementarea **analizei semantice**: rezolvarea simbolurilor și verificarea tipurilor pe baza regulilor din manualul limbajului.

## Obiective
- Construirea tabelelor de simboluri și rezolvarea referințelor.  
- Verificarea tipurilor pentru expresii și definiții (clase, atribute, metode, parametri).  
- Raportarea erorilor semantice cu informații despre fișier, linie și coloană.  
- Adnotarea AST-ului cu tipuri și simboluri pentru etapele următoare.  

## Erori semantice tratate
- Clase:
  - redefinire clasă, părinte nedefinit, părinte ilegal (`Int`, `String`, `Bool`, `SELF_TYPE`), cicluri de moștenire.  
- Atribute:
  - nume `self` interzis, redeclarare atribut, tip nedefinit, incompatibilitate la inițializare.  
- Metode:
  - redeclarare, tip întors nedefinit, inconsistență la overriding (parametri, tipuri, return).  
- Parametri formali:
  - nume `self` interzis, redeclarare, tip `SELF_TYPE` interzis, tip nedefinit.  
- Expresii `let` și `case`: variabile `self` ilegale, tipuri nedefinite sau incompatibile.  
- Variabile: utilizare fără definire.  
- Operatori: operanzi cu tipuri incorecte (`Int` pentru aritmetic/relational, `Bool` pentru `not`).  
- Atribuiri: nu se poate asigna lui `self`, incompatibilitate între tipuri.  
- `new` și `isvoid`: `new` pe tip nedefinit, `isvoid` întoarce mereu `Bool`.  
- `if` și `while`: condiții care nu au tip `Bool`.  
- Apeluri de metode: metodă nedefinită, număr incorect de argumente, tipuri incompatibile, apel static pe `SELF_TYPE`, tip static nedefinit sau care nu este superclasă.  
- Existența metodei `main` în clasa `Main`.  

## Structura proiectului
- `cool.compiler` – modulul principal.  
- `cool.lexer` – analizor lexical.  
- `cool.parser` – analizor sintactic.  
- `cool.structures` – tabele de simboluri, clase auxiliare pentru simboluri și tipuri, metode `defineBasicClasses()` și `SymbolTable.error()`.  
- `cool.tester` – modul de testare automată.  
- `tests/tema2` – programe `.cl` + output de referință `.ref`.  
