### Local Setup
1. Dependency
   1. PostgreSQL
   2. Java 8

2. Create database user `test_api` use password `test_api` when asked
    ```sql
    createuser -P -s  test_api
    ```
    
3. Create database named `test_api`
    ```sql
    createdb test_api
    ```

### Test and Build
1. Test
    ```cmd
    ./gradlew clean test
    ```

2. Build (include test)
    ```cmd
    ./gradlew clean build
    ```