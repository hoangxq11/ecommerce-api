
# Ecommerce Api




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


