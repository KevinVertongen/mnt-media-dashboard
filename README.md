# Media Dashboard

An overview of recently played media.

## Technologies

* Java 25 LTS
* JavaFX 26.0.1
* Spring Boot 4.1.0
* H2 file database

## Profiles

You *must* specify a Spring Profile as an environment variable when running this application.

```bash
spring.profiles.active=dev
```

| Profile | Description |
|---------|------------| 
| dev | Local development |
| prod | Production |
