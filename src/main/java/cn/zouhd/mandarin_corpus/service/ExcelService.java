package cn.zouhd.mandarin_corpus.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    boolean excelBatchImport(String filename, MultipartFile multipartFile) throws Exception;
}
