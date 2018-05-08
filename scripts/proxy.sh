export INSTANCE_CONNECTION_NAME="flock-61206:europe-west1:flock-data"
./cloud_sql_proxy -instances=${INSTANCE_CONNECTION_NAME}=tcp:3306