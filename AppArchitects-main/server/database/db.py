import sqlite3
from . import *


class Database:
    def __init__(self, db_path = "storage/database.sqlite3"):
        self.db_path = db_path
        self.DB: sqlite3.Connection = sqlite3.connect(db_path)
        self._init__tables_()

    def execute(self, statement, args: list = [], commit=True):
        cursor = self.DB.cursor()
        cursor.execute(statement, args)
        if commit:
            self.DB.commit()
        result = {
            "fetchAll": cursor.fetchall(),
            "fetchOne": cursor.fetchone(),
            "rowCount": cursor.rowcount(),
        }
        cursor.close()
        return result
        
    def _init__tables_(self):
        tables = [
            CREATE_TABLE_SOCIETY,
            CREATE_TABLE_SUPPLIER,
            CREATE_TABLE_TRANSACTION,
            CREATE_TABLE_SESSION,
        ]
        for query in tables:
            self.DB.execute(query)
        
        return None
    
    def newSession(self, farmerID, deviceDetail):
        return self.execute(
            " \
                INSERT INTO session (farmerID, deviceDetail) values ( ?, ? );\
            ",
            [(farmerID, deviceDetail)]
        )

    def getSessionID(self, farmerID, deviceDetail):
        return self.DB.execute(
            " \
                SELECT  sessionID FROM session \
                WHERE \
                    farmerID = ? AND \
                    deviceDetail = ?\
            ", [farmerID, deviceDetail]
        )

    def sessionIsValid(self, farmerID, sessionID, deviceDetail):
        return self.DB.execute(
            " \
                SELECT  DISTINCT(*) FROM session \
                WHERE \
                    farmerID = ? AND \
                    deviceDetail = ? AND\
                    sessionID = ?\
            ", 
            [(farmerID, deviceDetail, sessionID)]
        )

    def logoutSession(self, sessionID):
        return self.execute(
            " \
                DELETE FROM session WHERE sessionID = ?\
            ",
            [(sessionID,)]
        )
    
    def newSupplier(self, name, dob, contact, address, societyID):
        return self.execute(
            " \
                INSERT INTO supplier values (?, ?, ?, ?, ?, ?)\
            ",
            [(name, dob, contact, address, societyID)]
        )
    
    def saveSupplier(self, farmerID, name, dob, contact, address, societyID):
        return self.execute(" UPDATE supplier SET\
            name = ?, dob = ?, contactNo = ?, address = ?, societyID = ?\
            WHERE farmerID = ?\
        ",
        [(name, dob, contact, address, societyID, farmerID)]
    )

    def getSupplier(self, farmerID):
        return self.DB.execute("\
            SELECT * from supplier WHERE farmerID = ?\
        ", [(farmerID,)])
    
    def newTransaction(self, transactionID, farmerID, quantity, quality, rate, societyID):
        return self.execute(
            " \
                INSERT INTO transactions (transactionID, farmerID, quantity, quality, rate, societyID) values (?, ?, ?, ?, ?, ?)\
            ",
            [(transactionID, farmerID, quantity, quality, rate, societyID)]
        )
    

    def getSupplierTransactions(self, farmerID):
        return self.DB.execute("\
            SELECT * from transactions WHERE farmerID = ?\
        ", [(farmerID,)])
    

    def getSocietyTransactions(self, societyID):
        return self.DB.execute("\
            SELECT * from transactions WHERE societyID = ?\
        ", [(societyID,)])
    

    def getAllTransactions(self):
        return self.DB.execute("\
            SELECT * from transactions\
        ")
    

    def newSociety(self, farmerID, name, district, taluk, block, villagePanchayat, revenueVillage):
        return self.execute(
            " \
                INSERT INTO society (name, district, taluk, block, villagePanchayat, name, district, taluk, block, villagePanchayat) values (?, ?, ?, ?, ?, ?)\
            ",
            [(name, district, taluk, block, villagePanchayat, revenueVillage)]
        )
    

    def getSociety(self, societyID) :
        return self.DB.execute("\
            SELECT * FROM society WHERE societyID = ?\
        ", [(societyID,)])

    def editSociety(self, societyID, name, district, taluk, block, villagePanchayat, revenueVillage):
        return self.execute("\
            UPDATE society SET\
            name = ?, district = ?, taluk = ?, block = ?, villagePanchayat = ?, revenueVillage = ?\
            WHERE societyID = ?\
        ",
        [(name, district, taluk, block, villagePanchayat, revenueVillage, societyID)]
        )
    
    def deleteSociety(self, societyID):
        return self.execute(
            " \
                DELETE FROM society WHERE  societyID = ?\
            ",
            [(societyID,)]
        )
