package com.br.erik5594.util.arquivo;

import org.primefaces.model.UploadedFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static BufferedReader obterBufferReader(UploadedFile arquivo) throws IOException {
        InputStream inputStream = arquivo.getInputstream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }
}
