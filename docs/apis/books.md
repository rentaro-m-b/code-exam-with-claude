# books end-points

## /books uri

### GET methods

#### request header
NONE

#### request body
NONE

#### response header
```http
HTTP/1.1 200
```

#### response body
```json
{
  "books": [
    {
      "id": "1",
      "title": "テスト駆動開発",
      "author": "Kent Beck",
      "publisher": "オーム社",
      "price": 3080
    },
    {
      "id": "2",
      "title": "アジャイルサムライ",
      "author": "Jonathan Rasmusson",
      "publisher": "オーム社",
      "price": 2860
    }
  ]
}
```

### POST methods

#### request header
NONE

#### request body
```json
{
  "title": "Clean Agile",
  "author": "Robert C. Martin",
  "publisher": "ドワンゴ",
  "price": 2640
}
```

#### response header
```http
HTTP/1.1 201
Location: http://host.domain:port/books/3
```