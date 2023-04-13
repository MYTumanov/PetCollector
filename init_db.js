db = db.getSiblingDB('pet_collector');

db.createCollection('users', {capped:false});
db.createCollection('debts', {capped:false});

db.users.createIndex({"login":1}, {unique:true});

const res = db.users.insertMany([
  {login: "superlogin", password:"{noop}qwerty", phoneNumber:"+79254719928", firstName:"Максим", isDeleted:false, version:0},
  {login: "derevo666", password:"{noop}123", phoneNumber:"+79260519463", firstName:"Денис", isDeleted:false, version:0},
  {login: "tsapina", password:"{noop}еыфзштф", phoneNumber:"+79772666889", firstName:"Дарина", isDeleted:false, version:0}
]);

db.debts.insertMany([
  {totalSum:1100, ownerId:res.insertedIds[0], isDeleted:false, version:0, status:"IN_PROCCESS", debtors:[
    {userId:res.insertedIds[1], sum:500, phoneNumber:"+79260519463", status: "IN_PROCCESS"}
  ]},
  {totalSum:200, ownerId:res.insertedIds[1], isDeleted:false, version:0, status:"IN_PROCCESS", debtors:[
    {userId:res.insertedIds[0], sum:200, phoneNumber:"+79260519463", status: "IN_PROCCESS"},
    {userId:res.insertedIds[2], sum:200, phoneNumber:"+79772666889", status: "IN_PROCCESS"}
  ]},
  {totalSum:0, ownerId:res.insertedIds[0], isDeleted:false, version:0, status:"IN_PROCCESS", debtors:[
    {userId:res.insertedIds[1], sum:333, phoneNumber:"+79260519463", status: "IN_PROCCESS"},
    {userId:res.insertedIds[2], sum:335, phoneNumber:"+79772666889", status: "IN_PROCCESS"}
  ]}
])