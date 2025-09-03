# 이때만나(약속 시간 투표 서비스)

> 매번 카톡으로 약속 잡기가 너무 힘들 때,
> 간편하게 투표하고 즐겁게 놀도록!

## 주요 기능

### 1. 투표방 만들기

- 투표방을 만들고, 친구에게 `roomId`를 공유합니다.
    - 투표방을 만들 때는 시작 날짜, 종료 날짜, 시작 시간, 종료 시간, 익명 여부를 포함해야 합니다.
    - 익명 투표방이라면, 투표자 가입시 이름이 익명으로 설정됩니다.
- 예시

```text
POST http://localhost:8080/room
Content-Type: application/json

{
"startDate": "2025-05-20",
"endDate": "2025-05-30",
"startTime": "08:00",
"endTime": "22:00"
}

HTTP/1.1 201 
Location: /room/abcd1234
```

### 2. 투표자 가입하기

- 투표를 하기 위해서는 투표자로 가입해야 합니다.
    - 투표자의 이름을 지정하지 않은 경우에는 랜덤 이름이 생성됩니다.
- 익명 투표방의 경우에는 name 설정 여부와 상관없이 랜덤한 이름으로 생성됩니다.
- 예시

```text
POST http://localhost:8080/voter/abcd1234
Content-Type: application/json

{
  "name": "dompoo",
  "password": "1234"
}

HTTP/1.1 200 
Content-Type: application/json

{
  "name": "dompoo"
}
```

### 3. 투표하기

- 원하는 약속 시간을 포함하여 요청하면 투표가 완료됩니다.
- 예시

```text
POST http://localhost:8080/vote/abcd1234?name=dompoo&password=1234
Content-Type: application/json

{
  "values": [
    "2025-05-21T10:00",
    "2025-05-21T11:00",
    "2025-05-21T12:00"
  ]
}

HTTP/1.1 201 
Location: /time/abcd1234/my
```

### 4. 투표 통계 확인하기

- 전체 투표 현황을 확인해볼 수 있습니다.

```text
GET http://localhost:8080/vote/abcd1234

HTTP/1.1 200 
Content-Type: application/json

{
  "statics": [
    {
      "dateTime": "2025-05-21T10:00:00",
      "voterNames": [
        "dompoo",
        "dompoo2"
      ]
    },
    {
      "dateTime": "2025-05-21T11:00:00",
      "voterNames": [
        "dompoo",
        "dompoo2"
      ]
    }
  ]
}
```

## 부가 기능

### 1. 방 정보 확인

```text
GET http://localhost:8080/room/abcd1234

HTTP/1.1 200 
Content-Type: application/json

{
  "startDate": "2025-05-20",
  "endDate": "2025-05-30",
  "startTime": "08:00:00",
  "endTime": "22:00:00",
  "isAnonymousRoom": true,
  "voterNames": [dompoo, dompoo2]
}
```

### 2. 내 투표 확인

```text
GET http://localhost:8080/vote/abcd1234/my?name=dompoo&password=1234

HTTP/1.1 200 
Content-Type: application/json

{
  "name": "dompoo",
  "values": [
    "2025-05-21T10:00:00",
    "2025-05-21T11:00:00"
  ]
}
```

### 3. 다시 투표하기

```text
PUT http://localhost:8080/vote/abcd1234?name=dompoo&password=1234
Content-Type: application/json

{
  "values": [
    "2025-05-21T10:00",
    "2025-05-21T11:00",
    "2025-05-21T12:00"
  ]
}

HTTP/1.1 204
```

### 4. 내 투표 제거

```text
DELETE http://localhost:8080/vote/abcd1234?name=dompoo&password=1234

HTTP/1.1 204
```
