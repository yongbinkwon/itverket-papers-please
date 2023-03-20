docker-compose down
docker-compose up -d
docker-compose exec broker kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic itverket-immigration-0
docker-compose exec broker kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic itverket-immigration-1
docker-compose exec broker kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic itverket-immigration-2
docker-compose exec broker kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic itverket-immigration-3
docker-compose exec broker kafka-topics --create --bootstrap-server broker:9092 --replication-factor 1 --partitions 1 --topic itverket-immigration-result