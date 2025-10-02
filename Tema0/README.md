# Tema 0 – COOL Lists ;)

Proiect realizat pentru cursul **Compilatoare**, având ca scop familiarizarea cu limbajul didactic **COOL (Classroom Object-Oriented Language)**.  
Tema constă în implementarea unei **liste generice** care să poată conține obiecte de orice tip și să ofere funcționalități uzuale de manipulare.

## Cerințe
- Implementarea unei liste capabile să rețină elemente de orice tip
- Popularea listei cu obiecte citite de la STDIN
- Prompt interactiv pentru gestionarea listelor

## Obiecte disponibile
Clase definite în schelet:
- **Product** (Edible → Soda, Coffee; Laptop, Router)
- **Rank** (Private, Corporal, Sergent, Officer)
- Tipuri implicite COOL (String, Int, Bool, IO)

## Funcționalități implementate
- `help` – listează comenzile posibile
- `load` – încarcă o nouă listă de obiecte din STDIN
- `print` – afișează toate listele încărcate (sau una, dacă se specifică index)
- `merge i j` – concatenează lista j la lista i
- `filterBy index {ProductFilter, RankFilter, SamePriceFilter}` – filtrează lista după tip/condiții
- `sortBy index {PriceComparator, RankComparator, AlphabeticComparator} {asc/desc}` – sortează lista după criteriu
