# Orders parser

Сборка: 
```bash
./mvnw clean install
```

Запуск:
```bash
java -jar orders_parser.jar <filePath>
```

где **filePath**, путь до файла с описанием заказов.

Тестовый запуск из корня проекта после сборки:
```bash
java -jar target/orders_parser.jar ./files/orders1.csv ./files/orders2.json
```