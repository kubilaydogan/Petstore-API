
## Enum Class

```java
public enum Status {
    available, pending, sold
}
```

```java
System.out.println(Arrays.toString(Status.values()));       // [available, pending, sold]
System.out.println(Status.values()[0]);                      // available
```

#### Getting random value from enum

```java
Faker faker = new Faker();
System.out.println(Status.values()[faker.random().nextInt(Status.values().length)]);         // [available, pending, sold][0, 1, 2]
```
```java
Random random = new Random();
System.out.println(random.nextInt(3));      // [0, 1, 2]
```



## Request body (Add Pet)

```json
{
  "name": "...",
  "photoUrls": [
    "..."
  ]
}
```

```json
{
  "name": "...",
  "photoUrls": [
    "...",
    "..."
  ],
  "category": {
    "id": 100,
    "name": "..."
  },
  "tags": [
    {
      "id": 200,
      "name": "..."
    },
    {
      "id": 300,
      "name": "..."
    }
  ],
  "status": "available",
  "id": 777
}
```