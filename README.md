# ShareBook 

## Praca z Intellij IDEA

Po zaimportowaniu projektu jako `New Project from Version Control` należy kliknąć prawym na pliku ./backend/pom.xml i zaznaczyć `Add as maven project` 

### Uruchamianie aplikacji

#### Baza danych
Dla ułatwienia developmentu używamy bazy danych w pamięci (H2). Nie potrzebna jest dodatkowa konfiguracja. 

#### Backend
Z Intellij odpalić albo z terminala
```bash
cd backend
./mvnw spring-boot:run
```

#### Frontend
Z Intellij odpalić albo z terminala
```bash
cd frontend
npm start
```
