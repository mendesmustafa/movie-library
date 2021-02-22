# admin info

- username = admin
- password = 123

## admin processes

- [http://localhost:8080/movie/list]
- [http://localhost:8080/movie/add-movie]
- [http://localhost:8080/movie/edit/{id}]
- [http://localhost:8080/movie/delete/{id}]
- [http://localhost:8080/movie/sort-date]
- [http://localhost:8080/movie/search?search={name}]
- [http://localhost:8080/actor/list]
- [http://localhost:8080/actor/add-actor]
- [http://localhost:8080/actor/edit/{id}]
- [http://localhost:8080/actor/delete/{id}]
- [http://localhost:8080/user-movie/list]
- [http://localhost:8080/user-movie/add-movie]
- [http://localhost:8080/user-movie/sort-date]
- [http://localhost:8080/user-movie/search?search={name}]
- [http://localhost:8080/user-actor/list]
- [http://localhost:8080/user-actor/add-actor]




# user info

- username = user
- password = 123

## user processes

- [http://localhost:8080/user-movie/list]
- [http://localhost:8080/user-movie/add-movie]
- [http://localhost:8080/user-movie/sort-date]
- [http://localhost:8080/user-movie/search?search={name}]
- [http://localhost:8080/user-actor/list]
- [http://localhost:8080/user-actor/add-actor]


## Bilgiler

Admin tüm url'de yetkili.

User sadece belirtilen url'de yetkili.

Admin kaydı düzenleyebiliyor ve silebiliyor.

User kayıt yapabiliyor silme ve düzenleme yapamıyor.


## junit test

java 11 maven 3.5.2 ve üstünde çalışıyor.

