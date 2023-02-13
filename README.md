
# Ecommerce Api




## Installation

- To run this project, you will need to install java, mysql, git
- Clone project from github
- Run `data.sql` from `src/main/resources` in your Database Management System
- Open project in IDE which support for Spring and run project or use maven in command prompt


## Test api
- To test api access link: `localhost:port/context-path/swagger-ui.html` 
- You can change `port` and `context-path` in `application.properties` from `src/main/resources`

## API Reference

#### Authentication

```http
  POST /api/auth/signin
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username or email` | `string` | **Required**. Username/email |
| `password` | `string` | **Required**. Password |

#### Resgister new account

```http
  POST /api/auth/signup
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | **Required**. Email |
| `username`      | `string` | **Required**. Username |
| `password`      | `string` | **Required**. Password |
| `authorities`      | `list` | **Required**. List role |


