#### Demo project for testing CQRS and partial updating using domain events

Main module for now - data-jdbc

run project:  ./gradlew data-jdbc:bootRun
for testing UI:  http://localhost:8080
check database: http://localhost:8080/h2-console/
db password:
db user: sa
Check schema.sql to find useful info about tables structure and tables usage for different scenarios

To test some api use sh files in project root project:

- **renameCategoryApi.sh**
- **voteArticlesApi.sh**
- **renameCategoryApi.sh**
- **editParagraphInPort.sh** to test use before dropParagraphInPort.sh can be invoked multiple times operation is idempotent
- **dropParagraphInPort.sh** can invoke multiple times operation is idempotent
- **createCategoryApi.sh** can be invoked multiple times (name will be randomly generated)
- **createArticleApi.sh** can be invoked multiple times (name will be randomly generated)

TODO: functional approach and right error handling tests and other useful things
