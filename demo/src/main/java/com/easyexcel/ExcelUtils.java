package com.easyexcel;//package com.easyexcel;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.excel.ExcelReader;
//import com.alibaba.excel.ExcelWriter;
//
//
///**
// * Excel读写工具类
// * @author test
// */
//@SuppressWarnings("rawtypes")
//public class ExcelUtils {
//
//	private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
//
//	public static boolean isExcel(File file) {
//		String fileName = file.getName();
//
//		if (file.isFile()) {
//			int idx = fileName.lastIndexOf(".");
//			if (idx > 0) {
//				String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
//				if (".xls".equals(ext) || ".xlsx".equals(ext)) {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
//
//	/**
//	 * Excel写出
//	 * @param params
//	 */
//	public static <T> void writeExcel(ExcelParams<T> params) {
//
//		ExcelWriterSheetBuilder writerSheetBuilder = null;
//		ExcelWriter excelWriter = null;
//		try {
//
//			if (params.getFileDest() == null) {
//				logger.error("Excel写出失败，fileDest为空！");
//				return;
//			}
//
//		    excelWriter = EasyExcel.write(params.getFileDest(), params.getDataClass()).build();
//
//		    FreezePane fp = params.getFreezePane();
//
//		    writerSheetBuilder = EasyExcel.writerSheet(params.getSheetName());
//
//		    if (fp != null) {
//		    	writerSheetBuilder.registerWriteHandler(new SheetFreezeWriteHandler(fp));
//		    }
//
//		    WriteSheet writeSheet = writerSheetBuilder.build();
//		    excelWriter.write(params.getDataList(), writeSheet);
//		} finally {
//		    // 千万别忘记finish 会帮忙关闭流
//		    if (excelWriter != null) {
//		        excelWriter.finish();
//		    }
//		}
//	}
//
//	/**
//	 * Excel写出到response
//	 * @param params
//	 */
//	public static <T> void writeExcel(ExcelParams<T> params, HttpServletResponse response) {
//
//		response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        // 如果fileName为空，则使用当前时间戳为fileName
//        String fileName = StringUtils.defaultIfBlank(params.getFileName(), String.valueOf(System.currentTimeMillis()));
//		try {
//			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//
//		ExcelWriterSheetBuilder writerSheetBuilder = null;
//		ExcelWriter excelWriter = null;
//		try {
//		    excelWriter = EasyExcel.write(response.getOutputStream(), params.getDataClass()).build();
//
//		    FreezePane fp = params.getFreezePane();
//
//		    writerSheetBuilder = EasyExcel.writerSheet(params.getSheetName());
//
//		    if (fp != null) {
//		    	writerSheetBuilder.registerWriteHandler(new SheetFreezeWriteHandler(fp));
//		    }
//		    writerSheetBuilder.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
//
//		    WriteSheet writeSheet = writerSheetBuilder.build();
//		    excelWriter.write(params.getDataList(), writeSheet);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//		    // 千万别忘记finish 会帮忙关闭流
//		    if (excelWriter != null) {
//		        excelWriter.finish();
//		    }
//		}
//	}
//
//	/**
//	 * Execl异步读取
//	 * @param params
//	 * @return
//	 */
//	public static <T> void readExcel(ExcelParams<T> params) {
//        ExcelReader excelReader = null;
//        ReadSheet readSheet1 = null;
//        try {
//			if (params.getFileSrc() == null) {
//				logger.error("Excel读取失败，fileSrc为空！");
//				return;
//			}
//
//            excelReader = EasyExcel.read(params.getFileSrc()).autoTrim(true).build();
//
//            // 注册监听器
//            if (params.getListener() != null) {
//            	readSheet1 = EasyExcel.readSheet(0).head(params.getDataClass()).registerReadListener(params.getListener()).build();
//            } else {
//            	readSheet1 = EasyExcel.readSheet(0).head(params.getDataClass()).build();
//            }
//
//            excelReader.read(readSheet1);
//
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
//	}
//
//	/**
//	 * Execl同步读取
//	 * @param params
//	 * @return
//	 */
//	public static <T> List<T> readExcelSync(ExcelParams<T> params) {
//        ExcelReader excelReader = null;
//        ReadSheet readSheet = null;
//        try {
//			if (params.getFileSrc() == null) {
//				logger.error("Excel读取失败，fileSrc为空！");
//				return new ArrayList<T>();
//			}
//
//            excelReader = EasyExcel.read(params.getFileSrc()).autoTrim(true).build();
//
//            // 注册监听器
//            SyncReadListener syncReadListener = new SyncReadListener();
//            if (params.getListener() != null) {
//            	readSheet = EasyExcel.readSheet(0).head(params.getDataClass())
//            			.registerReadListener(syncReadListener).registerReadListener(params.getListener()).build();
//            } else {
//            	readSheet = EasyExcel.readSheet(0).head(params.getDataClass()).registerReadListener(syncReadListener).build();
//            }
//
//            excelReader.read(readSheet);
//
//            return (List<T>)syncReadListener.getList();
//
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
//	}
//
//
//	/**
//	 * Excel多sheet导出
//	 * @param params
//	 */
//	public static void writeExcelMulti(ExcelParamsMulti paramsMulti) {
//
//		ExcelWriter excelWriter = null;
//		try {
//			if (paramsMulti.getFileDest() == null) {
//				logger.error("Excel写出失败，fileDest为空！");
//				return;
//			}
//
//			Class[] dataClass = paramsMulti.getDataClassArray();
//			String[] sheetNames = paramsMulti.getSheetName();
//			FreezePane[] fp = paramsMulti.getFreezePane();
//			List[] dataListArray = paramsMulti.getDataListArray();
//
//			excelWriter = EasyExcel.write(paramsMulti.getFileDest()).build();
//
//			for (int i = 0; i < sheetNames.length; i ++) {
//
//				ExcelWriterSheetBuilder writerSheetBuilder = EasyExcel.writerSheet(sheetNames[i]).head(dataClass[i]);
//				if (fp != null && fp[i] != null) {
//					writerSheetBuilder.registerWriteHandler(new SheetFreezeWriteHandler(fp[i]));
//				}
//
//				WriteSheet writeSheet = writerSheetBuilder.build();
//			    excelWriter.write(dataListArray[i], writeSheet);
//			}
//
//		} finally {
//		    // 千万别忘记finish 会帮忙关闭流
//		    if (excelWriter != null) {
//		        excelWriter.finish();
//		    }
//		}
//	}
//
//	/**
//	 * Execl多sheet异步读取
//	 * @param params
//	 * @return
//	 */
//	public static void readExcelMulti(ExcelParamsMulti paramsMulti) {
//
//		List<ReadSheet> sheetList = new ArrayList<>();
//		ExcelReader excelReader = null;
//        try {
//        	if (paramsMulti.getFileSrc() == null) {
//				logger.error("Excel读取失败，fileSrc为空！");
//				return;
//			}
//
//			Class[] dataClass = paramsMulti.getDataClassArray();
//			AnalysisEventListener[] listener = paramsMulti.getListenerArray();
//
//			excelReader = EasyExcel.read(paramsMulti.getFileSrc()).autoTrim(true).build();
//
//			for (int i = 0; i < dataClass.length; i ++) {
//				ExcelReaderSheetBuilder readerSheetBuilder = EasyExcel.readSheet(i).head(dataClass[i]);
//
//				// 注册监听器
//	            if (listener != null && listener[i] != null) {
//	            	readerSheetBuilder.registerReadListener(listener[i]);
//	            }
//
//	            ReadSheet readSheet = readerSheetBuilder.build();
//	            sheetList.add(readSheet);
//			}
//
//			excelReader.read(sheetList);
//
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
//	}
//
//	/**
//	 * Execl多sheet同步读取
//	 * @param <T>
//	 * @param params
//	 * @return
//	 */
//	public static List[] readExcelMultiSync(ExcelParamsMulti paramsMulti) {
//		List[] dataListArray = null;
//		List<ReadSheet> sheetList = new ArrayList<>();
//        ExcelReader excelReader = null;
//        try {
//        	if (paramsMulti.getFileSrc() == null) {
//				logger.error("Excel读取失败，fileSrc为空！");
//				return new ArrayList[0];
//			}
//
//			Class[] dataClass = paramsMulti.getDataClassArray();
//			AnalysisEventListener[] listener = paramsMulti.getListenerArray();
//
//			excelReader = EasyExcel.read(paramsMulti.getFileSrc()).autoTrim(true).build();
//
//			dataListArray = new ArrayList[dataClass.length];
//			SyncReadListener[] listenerArray = new SyncReadListener[dataClass.length];
//
//			for (int i = 0; i < dataClass.length; i ++) {
//				ExcelReaderSheetBuilder readerSheetBuilder = EasyExcel.readSheet(i).head(dataClass[i]);
//
//				// 注册监听器
//	            if (listener != null && listener[i] != null) {
//	            	readerSheetBuilder.registerReadListener(listener[i]);
//	            }
//	            SyncReadListener syncReadListener = new SyncReadListener();
//	            readerSheetBuilder.registerReadListener(syncReadListener);
//
//	            ReadSheet readSheet = readerSheetBuilder.build();
//	            sheetList.add(readSheet);
//	            listenerArray[i] = syncReadListener;
//			}
//
//			excelReader.read(sheetList);
//
//            for (int i = 0; i < listenerArray.length; i ++) {
//            	dataListArray[i] = listenerArray[i].getList();
//            }
//
//            return dataListArray;
//
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
//	}
//
//}