#!/bin/bash

# Wait for MySQL to be available on port 3306
while nc -z -w 1 mysql-host 3306; do
 echo "Waiting for MySQL to be available on port 3306..."
 sleep 1
done

# Once MySQL is available, execute the Java command
exec java -cp app:app/lib/* es.uvigo.dagss.recetas.RecetasApplication