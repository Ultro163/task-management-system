db = db.getSiblingDB('mongodb-tms');

db.createUser({
    user: "ultro",
    pwd: "163163",
    roles: [{ role: "readWrite", db: "mongodb-tms" }]
});
