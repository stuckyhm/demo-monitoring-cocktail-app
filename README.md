
# Demo-Projekt Monitoring - CocktailApp

## Starten
```
git clone git@github.com:stuckyhm/demo-monitoring-cocktail-app.git
cd demo-monitoring-cocktail-app/parent
docker network create monitoring_ext
./mvnw clean install
docker-compose up
```

## Testaufrufe

### App
```
curl -i http://127.0.0.1:9000/content -u a_user:resu_a   # NO_SUBSCRIPTION
curl -i http://127.0.0.1:9000/content -u b_user:resu_b   # Access Denied
curl -i http://127.0.0.1:9000/content -u c_user:resu_c   # STANDARD
curl -i http://127.0.0.1:9000/content -u i_user:resu_i   # PLATIN
curl -i http://127.0.0.1:9000/content -u s_user:resu_s   # PREMIUM
```

### Auth-Service
```
curl -i -X POST -F 'username=a_user' -F "passwordHash=81f2ed7b096ecf438c0616f0eec56024234ad459" http://127.0.0.1:9001/auth 
```

### Billing-Service
```
curl -i http://127.0.0.1:9002/subscriptions/a_user
```

### Content-Service
```
curl -i http://127.0.0.1:9003/contents
```
