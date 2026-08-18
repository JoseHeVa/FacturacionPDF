package com.example.facturacionpdf.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FacturacionDatabase_Impl extends FacturacionDatabase {
  private volatile FacturacionDao _facturacionDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cliente_entity` (`rncCedula` TEXT NOT NULL, `nombre` TEXT NOT NULL, `direccion` TEXT NOT NULL, PRIMARY KEY(`rncCedula`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `producto_entity` (`idProducto` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT NOT NULL, `precio` REAL NOT NULL, `stock` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `factura_entity` (`idFactura` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `clienteRnc` TEXT NOT NULL, `productoId` INTEGER NOT NULL, `cantidad` INTEGER NOT NULL, `total` REAL NOT NULL, `fecha` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '28d15115fda6f46522f34af15b8cdec6')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `cliente_entity`");
        db.execSQL("DROP TABLE IF EXISTS `producto_entity`");
        db.execSQL("DROP TABLE IF EXISTS `factura_entity`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsClienteEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsClienteEntity.put("rncCedula", new TableInfo.Column("rncCedula", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClienteEntity.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClienteEntity.put("direccion", new TableInfo.Column("direccion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClienteEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClienteEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClienteEntity = new TableInfo("cliente_entity", _columnsClienteEntity, _foreignKeysClienteEntity, _indicesClienteEntity);
        final TableInfo _existingClienteEntity = TableInfo.read(db, "cliente_entity");
        if (!_infoClienteEntity.equals(_existingClienteEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "cliente_entity(com.example.facturacionpdf.database.ClienteEntity).\n"
                  + " Expected:\n" + _infoClienteEntity + "\n"
                  + " Found:\n" + _existingClienteEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsProductoEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsProductoEntity.put("idProducto", new TableInfo.Column("idProducto", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductoEntity.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductoEntity.put("precio", new TableInfo.Column("precio", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductoEntity.put("stock", new TableInfo.Column("stock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductoEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProductoEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProductoEntity = new TableInfo("producto_entity", _columnsProductoEntity, _foreignKeysProductoEntity, _indicesProductoEntity);
        final TableInfo _existingProductoEntity = TableInfo.read(db, "producto_entity");
        if (!_infoProductoEntity.equals(_existingProductoEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "producto_entity(com.example.facturacionpdf.database.ProductoEntity).\n"
                  + " Expected:\n" + _infoProductoEntity + "\n"
                  + " Found:\n" + _existingProductoEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsFacturaEntity = new HashMap<String, TableInfo.Column>(6);
        _columnsFacturaEntity.put("idFactura", new TableInfo.Column("idFactura", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacturaEntity.put("clienteRnc", new TableInfo.Column("clienteRnc", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacturaEntity.put("productoId", new TableInfo.Column("productoId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacturaEntity.put("cantidad", new TableInfo.Column("cantidad", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacturaEntity.put("total", new TableInfo.Column("total", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFacturaEntity.put("fecha", new TableInfo.Column("fecha", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFacturaEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFacturaEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFacturaEntity = new TableInfo("factura_entity", _columnsFacturaEntity, _foreignKeysFacturaEntity, _indicesFacturaEntity);
        final TableInfo _existingFacturaEntity = TableInfo.read(db, "factura_entity");
        if (!_infoFacturaEntity.equals(_existingFacturaEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "factura_entity(com.example.facturacionpdf.database.FacturaEntity).\n"
                  + " Expected:\n" + _infoFacturaEntity + "\n"
                  + " Found:\n" + _existingFacturaEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "28d15115fda6f46522f34af15b8cdec6", "3118941337fdfb5e2ebb1e96601131e3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cliente_entity","producto_entity","factura_entity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `cliente_entity`");
      _db.execSQL("DELETE FROM `producto_entity`");
      _db.execSQL("DELETE FROM `factura_entity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FacturacionDao.class, FacturacionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public FacturacionDao facturacionDao() {
    if (_facturacionDao != null) {
      return _facturacionDao;
    } else {
      synchronized(this) {
        if(_facturacionDao == null) {
          _facturacionDao = new FacturacionDao_Impl(this);
        }
        return _facturacionDao;
      }
    }
  }
}
