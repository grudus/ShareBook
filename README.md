# ShareBook 

## Praca z Intellij IDEA

Po zaimportowaniu projektu jako `New Project from Version Control` należy kliknąć prawym na pliku ./backend/pom.xml i zaznaczyć `Add as maven project` 

### Uruchamianie aplikacji

#### Mysql
Na ten moment jedyną zależnościa jest mysql. Zdefiniowana jest w pliku `./docker-compose.yml`.
Aby uruchomić należy wykonać polecenie:
```bash
docker-compose up -d
```

Jak się nie chce używać dockera (chociaż fajne to jest) to można samemu postawić mysql (byle port, użytkownik i hasło odpowiadały zdefiniowanym w `docker-compose.yml`)


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
