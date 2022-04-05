db = db.getSiblingDB('mongodb-integrador')


//db.myCollectionName.createIndex({ "address.zip": 1 }, { unique: false });
db.favorites.insert(
[
    { 
        "nombre": "Pago a Edelnor", 
        "tipo": "Servicios Basicos",
        "codigoServicio":"SER001"
    },
    {
        "nombre": "Pago a Calida", 
        "tipo": "Servicios Basicos" ,
        "codigoServicio": "SER002"
    },
    { 
        "nombre": "Pago a Movistar", 
        "tipo": "Servicios Telefonicos",
        "codigoServicio":"SER003"
    },
    { 
        "nombre": "Pago a Sedapal", 
        "tipo": "Servicios Basicos",
        "codigoServicio":"SER004" 
    }
]
);

