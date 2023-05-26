import org.rocksdb.*;

public class RocksDBDataReader {

    public static void main(String[] args) throws RocksDBException {
        // 打开RocksDB数据库
        RocksDB rocksDB = RocksDB.openReadOnly(new Options(), "/opt/homebrew/opt/rocksdb");

        // 创建RocksDB迭代器
        RocksIterator iterator = rocksDB.newIterator();
        iterator.seekToFirst();

        // 遍历数据库中的数据
        while (iterator.isValid()) {
            byte[] key = iterator.key();
            byte[] value = iterator.value();

            // 处理键值对数据
            String keyStr = new String(key);
            String valueStr = new String(value);

            System.out.println("Key: " + keyStr + ", Value: " + valueStr);

            iterator.next();
        }

        // 关闭RocksDB和迭代器
        iterator.close();
        rocksDB.close();
    }
}
