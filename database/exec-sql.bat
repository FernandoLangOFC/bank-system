docker cp ./database/drop_tables.sql falkon-database:/home/sql/drop_tables.sql
docker exec "falkon-database" psql "falkon-master" "falkon-master" -f /home/sql/drop_tables.sql

docker cp ./database/sql_file.sql falkon-database:/home/sql/sql_file.sql
docker exec "falkon-database" psql "falkon-master" "falkon-master" -f /home/sql/sql_file.sql