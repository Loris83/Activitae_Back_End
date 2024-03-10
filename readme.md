# Description du Projet

Ce projet est composé de plusieurs entités, chacune ayant un rôle spécifique dans le système. Voici une brève description de chaque composant :

## Entités

Les entités sont responsables de la génération de la base de données.

## Controllers

Les contrôleurs contiennent les chemins permettant d'effectuer des requêtes avec la base de données.

## Services

Les services regroupent toutes les fonctions appelées dans les contrôleurs. Ils facilitent la liaison entre les contrôleurs et les référentiels (repositories) qui établissent des liens directs avec la base de données.

## Packages Request

Dans le package "request", les classes se terminant par "request" contiennent les informations nécessaires pour les requêtes. Les classes se terminant par "response" permettent de personnaliser les retours.

## Chemins (Paths)

### User

- `/api/users/login`
- `/api/users/register`
- `/api/users/get-self`
- `/api/users/get/{id}`
- `/api/users/get-favorite`
- `/api/users/get-history`
- `/api/users/add-favorite/{id}`
- `/api/users/add-history/{id}`
- `/api/users/delete-favorite/{id}`
- `/api/users/set-self`
- `/api/users/delete-self/{id}`

### ActivityRegistration

- `/api/registration/register/{id}`
- `/api/registration/unregister/{id}`

### ActivityReport

- `/api/report/{id}`

### Chat

- `/api/chat/get/{id}`
- `/api/chat/send/{id}`

### Pictures

- `/api/pictures/upload`
- `/api/pictures/get/{id}`
- `/api/pictures/view/{id}`
- `/api/pictures/set-avatar`
- `/api/pictures/get-avatar`

### Thematiques

- `/api/thematiques/get/{id}`
- `/api/thematiques/get`
- `/api/thematiques/add-thematique`

### Tickets

- `/api/ticket/get/{id}`
- `/api/ticket/create`
