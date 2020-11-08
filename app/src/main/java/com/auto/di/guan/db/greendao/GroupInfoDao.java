package com.auto.di.guan.db.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.auto.di.guan.db.GroupInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GROUP_INFO".
*/
public class GroupInfoDao extends AbstractDao<GroupInfo, Long> {

    public static final String TABLENAME = "GROUP_INFO";

    /**
     * Properties of entity GroupInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property GroupId = new Property(1, int.class, "groupId", false, "GROUP_ID");
        public final static Property UserId = new Property(2, int.class, "userId", false, "USER_ID");
        public final static Property GroupName = new Property(3, String.class, "groupName", false, "GROUP_NAME");
        public final static Property GroupStatus = new Property(4, int.class, "groupStatus", false, "GROUP_STATUS");
        public final static Property GroupImage = new Property(5, int.class, "groupImage", false, "GROUP_IMAGE");
        public final static Property GroupLevel = new Property(6, int.class, "groupLevel", false, "GROUP_LEVEL");
        public final static Property GroupTime = new Property(7, int.class, "groupTime", false, "GROUP_TIME");
        public final static Property GroupRunTime = new Property(8, int.class, "groupRunTime", false, "GROUP_RUN_TIME");
        public final static Property GroupIsJoin = new Property(9, int.class, "groupIsJoin", false, "GROUP_IS_JOIN");
        public final static Property GroupStop = new Property(10, int.class, "groupStop", false, "GROUP_STOP");
    }


    public GroupInfoDao(DaoConfig config) {
        super(config);
    }
    
    public GroupInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GROUP_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GROUP_ID\" INTEGER NOT NULL ," + // 1: groupId
                "\"USER_ID\" INTEGER NOT NULL ," + // 2: userId
                "\"GROUP_NAME\" TEXT," + // 3: groupName
                "\"GROUP_STATUS\" INTEGER NOT NULL ," + // 4: groupStatus
                "\"GROUP_IMAGE\" INTEGER NOT NULL ," + // 5: groupImage
                "\"GROUP_LEVEL\" INTEGER NOT NULL ," + // 6: groupLevel
                "\"GROUP_TIME\" INTEGER NOT NULL ," + // 7: groupTime
                "\"GROUP_RUN_TIME\" INTEGER NOT NULL ," + // 8: groupRunTime
                "\"GROUP_IS_JOIN\" INTEGER NOT NULL ," + // 9: groupIsJoin
                "\"GROUP_STOP\" INTEGER NOT NULL );"); // 10: groupStop
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GROUP_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GroupInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGroupId());
        stmt.bindLong(3, entity.getUserId());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(4, groupName);
        }
        stmt.bindLong(5, entity.getGroupStatus());
        stmt.bindLong(6, entity.getGroupImage());
        stmt.bindLong(7, entity.getGroupLevel());
        stmt.bindLong(8, entity.getGroupTime());
        stmt.bindLong(9, entity.getGroupRunTime());
        stmt.bindLong(10, entity.getGroupIsJoin());
        stmt.bindLong(11, entity.getGroupStop());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GroupInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getGroupId());
        stmt.bindLong(3, entity.getUserId());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(4, groupName);
        }
        stmt.bindLong(5, entity.getGroupStatus());
        stmt.bindLong(6, entity.getGroupImage());
        stmt.bindLong(7, entity.getGroupLevel());
        stmt.bindLong(8, entity.getGroupTime());
        stmt.bindLong(9, entity.getGroupRunTime());
        stmt.bindLong(10, entity.getGroupIsJoin());
        stmt.bindLong(11, entity.getGroupStop());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public GroupInfo readEntity(Cursor cursor, int offset) {
        GroupInfo entity = new GroupInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // groupId
            cursor.getInt(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // groupName
            cursor.getInt(offset + 4), // groupStatus
            cursor.getInt(offset + 5), // groupImage
            cursor.getInt(offset + 6), // groupLevel
            cursor.getInt(offset + 7), // groupTime
            cursor.getInt(offset + 8), // groupRunTime
            cursor.getInt(offset + 9), // groupIsJoin
            cursor.getInt(offset + 10) // groupStop
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GroupInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGroupId(cursor.getInt(offset + 1));
        entity.setUserId(cursor.getInt(offset + 2));
        entity.setGroupName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGroupStatus(cursor.getInt(offset + 4));
        entity.setGroupImage(cursor.getInt(offset + 5));
        entity.setGroupLevel(cursor.getInt(offset + 6));
        entity.setGroupTime(cursor.getInt(offset + 7));
        entity.setGroupRunTime(cursor.getInt(offset + 8));
        entity.setGroupIsJoin(cursor.getInt(offset + 9));
        entity.setGroupStop(cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(GroupInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(GroupInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(GroupInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
