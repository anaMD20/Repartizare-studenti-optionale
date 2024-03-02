# Repartizare-studenti-optionale

Clasa STUDENT

Clasa Student reprezintă un student universitar. Fiecare student are un nume, o medie, un curs la care este înscris și o
listă de preferințe pentru cursuri.

Motivul Alegerii ArrayList pentru Preferințe: ArrayList este folosit pentru a stoca preferințele unui student datorită
flexibilității sale în adăugarea și accesarea cursurilor. Aceasta oferă performanță eficientă pentru operațiunile de
adăugare și iterare, care sunt frecvente în gestionarea preferințelor studenților.

Clasa CURS

Clasa Curs reprezintă un curs universitar. Un curs are un nume, o capacitate (număr maxim de studenți) și un set de
studenți înscriși.

Motivul Alegerii TreeSet pentru Stocarea Studenților: TreeSet este ales pentru a menține studenții înscriși în ordine
alfabetică și pentru a preveni duplicarea. Aceasta oferă un avantaj în menținerea unei liste ordonate și unice de
studenți, facilitând astfel gestionarea și accesarea rapidă a datelor.

Clasa SECRETARIAT

Clasa Secretariat gestionează operațiunile administrative, cum ar fi adăugarea studenților și cursurilor, citirea și
postarea mediilor, gestionarea contestațiilor și repartizarea studenților la cursuri.

Motivul Alegerii ArrayList pentru Studenți și Cursuri: Folosirea ArrayList permite o gestionare flexibilă și dinamică a
listelor de studenți și cursuri, oferind performanțe bune pentru operațiunile de adăugare și accesare.

Clasele StudentLicenta și StudentMaster

Aceste clase extind clasa Student și reprezintă studenți de licență și, respectiv, master. Ele nu adaugă funcționalități
 suplimentare, ci doar diferențiază tipurile de studenți.

Clasa StudentExistaExceptie

O excepție personalizată folosită pentru a semnala situații în care un student există deja în sistem.

Clasa MAIN

Clasa Main conține metoda main, aceasta inițializează un obiect Secretariat și procesează comenzi dintr-un fișier de
intrare, executând operațiunile corespunzătoare.

