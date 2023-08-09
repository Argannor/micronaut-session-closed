docker run --rm --name sample_maria -e MARIADB_USER=abc \
 -e MARIADB_PASSWORD=abc \
 -e MARIADB_ROOT_PASSWORD=abcroot \
 -e MARIADB_DATABASE=abc \
 -p 3336:3306 \
 -v mariadb-sample-micronaut-session-closed:/var/lib/mysql \
 mariadb:10