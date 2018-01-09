package it.polimi.deib.p2pchat.discovery.utilities;

/**
 * Created by brenocruz on 1/27/17.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by Breno D. Cruz on 11/7/16.
 * Reference[1] : http://www.java-tips.org/java-se-tips-100019/38/38-java-util-zip/1718-how-to-compress-a-byte-array.html
 * Reference[2] : http://www.java-tips.org/java-se-tips-100019/38-java-util-zip/1721-how-to-uncompress-a-file-in-the-gzip-format.html
 */

public final class CompressionUtils {
    //[1]
    public static byte[] CompressArray(byte[] input){


        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        compressor.setInput(input);
        compressor.finish();


        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
        byte[] output = new byte[1024];

        while (!compressor.finished()){

            int count = compressor.deflate(output);
            bos.write(output, 0, count);

        }

        try{

            bos.close();

        } catch (IOException e){

        }

        byte[] compressedData = bos.toByteArray();

        return compressedData;
    }
    //[2]
    public static byte[] DecompressArray(byte[] input){


        Inflater decompressor = new Inflater();
        decompressor.setInput(input);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

        byte[] output = new byte[1024];
        while (!decompressor.finished()){

            try{

                int count = decompressor.inflate(output);
                bos.write(output, 0, count);

            } catch (DataFormatException e){

            }
        }

        try{

            bos.close();

        } catch (IOException e){

        }

        byte[] decompressedData = bos.toByteArray();

        return decompressedData;
    }

}

