package com.litesoftwares.covidcheckbot.service;

import com.litesoftwares.model.Tables;
import com.litesoftwares.model.tables.Users;
import com.litesoftwares.model.tables.records.ReportsRecord;
import com.litesoftwares.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
  @Autowired
  private DSLContext dslContext;

  @Autowired
  public DatabaseService(DSLContext dslContext){
      this.dslContext = dslContext;
  }


  public UsersRecord selectUser(long userId){
       UsersRecord record = new UsersRecord();
      try {

          record = this.dslContext.selectFrom(Tables.USERS)
                  .where(Tables.USERS.USER_ID.eq(userId))
                  .fetchSingle();
      } catch (DataAccessException e){
          e.printStackTrace();
      }

      return record;
  }

    public void saveReport(long userId, String username, String name, String message, String platform) {
        try {
            ReportsRecord reportsRecord = new ReportsRecord();
            reportsRecord.setUserId(userId);
            reportsRecord.setUsername(username);
            reportsRecord.setName(name);
            reportsRecord.setMessage(message);
            reportsRecord.setPlatform(platform);

            reportsRecord.attach(this.dslContext.configuration());
            reportsRecord.store();

        } catch (DataAccessException e){
            e.printStackTrace();
        }
    }

  public void registerUser(long userId, String username, String name, String platform){
    try {
        UsersRecord usersRecord = new UsersRecord();
        usersRecord.setUserId(userId);
        usersRecord.setUsername(username);
        usersRecord.setName(name);
        usersRecord.setPlatform(platform);
        usersRecord.setStateReply("");

        usersRecord.attach(this.dslContext.configuration());
        usersRecord.store();

    } catch (DataAccessException e){
        e.printStackTrace();
    }

  }

    public void updateStateReply(String stateReply, long userId){
        try {
            this.dslContext
                    .update(Tables.USERS)
                    .set(Users.USERS.STATE_REPLY, stateReply)
                    .where(Users.USERS.USER_ID.eq(userId))
                    .execute();
        } catch (DataAccessException e){
            e.printStackTrace();
        }
    }

    public String fetchStateReply(long userId){
      String response = "";
      try {

          response = this.dslContext.select(Tables.USERS.STATE_REPLY).from(Tables.USERS)
                  .where(Tables.USERS.USER_ID.eq(userId))
                  .fetchOne(Users.USERS.STATE_REPLY);
      } catch (DataAccessException e){
          e.printStackTrace();
      }

      return response;
    }

    public boolean isRegistered(long userId) {
       boolean response = false;
      try {

         int count = this.dslContext.selectFrom(Tables.USERS)
                  .where(Tables.USERS.USER_ID.eq(userId))
                  .execute();
         if (count == 1 ){
             response = true;
         }

      } catch (DataAccessException e) {
          e.printStackTrace();
      }

      return response;
  }
}
