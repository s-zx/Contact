import org.rocksdb.*;

//public class RocksDBDelete {
//    public static void main(String[] args) {
//        // 指定RocksDB数据库路径
//        String dbPath = "/opt/homebrew/opt/rocksdb";
//
//        // 打开RocksDB数据库
//        try (RocksDB db = RocksDB.openReadOnly(new Options(), dbPath)) {
//            // 删除指定的键
//            byte[] key = "1684911180950".getBytes();
//            db.delete(key);
//
//            System.out.println("数据删除成功");
//        } catch (RocksDBException e) {
//            System.err.println("删除数据时出现错误：" + e.getMessage());
//        }
//    }
//}
public class RocksDBDelete {
    public static void main(String[] args) {
        // 指定RocksDB数据库路径
        String dbPath = "/opt/homebrew/opt/rocksdb";
        byte[] startKey = "1684911180952".getBytes();
        byte[] endKey = "1684912983857".getBytes();

        // 打开RocksDB数据库
        try (final Options options = new Options().setCreateIfMissing(true);
             final RocksDB db = RocksDB.open(options, dbPath)) {

            // 删除指定的键
            byte[] key = "1684912983857".getBytes();
            db.delete(key);

//            WriteOptions writeOptions = new WriteOptions().setDisableWAL(true);
//            db.deleteRange(writeOptions, startKey, endKey);


            System.out.println("数据删除成功");
        } catch (RocksDBException e) {
            System.err.println("删除数据时出现错误：" + e.getMessage());
        }
    }
}

