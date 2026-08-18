package com.example.facturacionpdf.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FacturacionDao_Impl implements FacturacionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductoEntity> __insertionAdapterOfProductoEntity;

  private final EntityInsertionAdapter<ClienteEntity> __insertionAdapterOfClienteEntity;

  private final EntityInsertionAdapter<FacturaEntity> __insertionAdapterOfFacturaEntity;

  private final EntityDeletionOrUpdateAdapter<ProductoEntity> __updateAdapterOfProductoEntity;

  public FacturacionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductoEntity = new EntityInsertionAdapter<ProductoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `producto_entity` (`idProducto`,`nombre`,`precio`,`stock`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductoEntity entity) {
        statement.bindLong(1, entity.getIdProducto());
        statement.bindString(2, entity.getNombre());
        statement.bindDouble(3, entity.getPrecio());
        statement.bindLong(4, entity.getStock());
      }
    };
    this.__insertionAdapterOfClienteEntity = new EntityInsertionAdapter<ClienteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cliente_entity` (`rncCedula`,`nombre`,`direccion`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ClienteEntity entity) {
        statement.bindString(1, entity.getRncCedula());
        statement.bindString(2, entity.getNombre());
        statement.bindString(3, entity.getDireccion());
      }
    };
    this.__insertionAdapterOfFacturaEntity = new EntityInsertionAdapter<FacturaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `factura_entity` (`idFactura`,`clienteRnc`,`productoId`,`cantidad`,`total`,`fecha`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FacturaEntity entity) {
        statement.bindLong(1, entity.getIdFactura());
        statement.bindString(2, entity.getClienteRnc());
        statement.bindLong(3, entity.getProductoId());
        statement.bindLong(4, entity.getCantidad());
        statement.bindDouble(5, entity.getTotal());
        statement.bindString(6, entity.getFecha());
      }
    };
    this.__updateAdapterOfProductoEntity = new EntityDeletionOrUpdateAdapter<ProductoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `producto_entity` SET `idProducto` = ?,`nombre` = ?,`precio` = ?,`stock` = ? WHERE `idProducto` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductoEntity entity) {
        statement.bindLong(1, entity.getIdProducto());
        statement.bindString(2, entity.getNombre());
        statement.bindDouble(3, entity.getPrecio());
        statement.bindLong(4, entity.getStock());
        statement.bindLong(5, entity.getIdProducto());
      }
    };
  }

  @Override
  public Object insertProducto(final ProductoEntity producto,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProductoEntity.insertAndReturnId(producto);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCliente(final ClienteEntity cliente,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfClienteEntity.insert(cliente);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFactura(final FacturaEntity factura,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFacturaEntity.insertAndReturnId(factura);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProducto(final ProductoEntity producto,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProductoEntity.handle(producto);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getProductoById(final int id,
      final Continuation<? super ProductoEntity> $completion) {
    final String _sql = "SELECT * FROM producto_entity WHERE idProducto = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductoEntity>() {
      @Override
      @Nullable
      public ProductoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdProducto = CursorUtil.getColumnIndexOrThrow(_cursor, "idProducto");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfPrecio = CursorUtil.getColumnIndexOrThrow(_cursor, "precio");
          final int _cursorIndexOfStock = CursorUtil.getColumnIndexOrThrow(_cursor, "stock");
          final ProductoEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpIdProducto;
            _tmpIdProducto = _cursor.getInt(_cursorIndexOfIdProducto);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final double _tmpPrecio;
            _tmpPrecio = _cursor.getDouble(_cursorIndexOfPrecio);
            final int _tmpStock;
            _tmpStock = _cursor.getInt(_cursorIndexOfStock);
            _result = new ProductoEntity(_tmpIdProducto,_tmpNombre,_tmpPrecio,_tmpStock);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getClienteByRnc(final String rnc,
      final Continuation<? super ClienteEntity> $completion) {
    final String _sql = "SELECT * FROM cliente_entity WHERE rncCedula = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, rnc);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ClienteEntity>() {
      @Override
      @Nullable
      public ClienteEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRncCedula = CursorUtil.getColumnIndexOrThrow(_cursor, "rncCedula");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
          final ClienteEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpRncCedula;
            _tmpRncCedula = _cursor.getString(_cursorIndexOfRncCedula);
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpDireccion;
            _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            _result = new ClienteEntity(_tmpRncCedula,_tmpNombre,_tmpDireccion);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
