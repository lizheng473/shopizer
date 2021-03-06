package com.logo.eshow.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

/**
 * 图片处理类
 * 
 * @author leida
 */
public class ImageUtil {

	/**
	 * @param path
	 * @param fileName
	 * @param url
	 * @param w
	 * @param h
	 * @param type
	 * @return boolean
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws MagickException
	 */
	public static boolean downloadImage(String path, String fileName,
			String url, int w, int h, String type) throws IOException,
			MagickException {
		try {
			String uploadDir = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "/" + path;

			File file = new File(uploadDir + "orig/");
			if (!file.exists() || !file.isDirectory()) {
				file.mkdirs();
			}

			InputStream stream = new URL(url).openConnection().getInputStream();

			OutputStream bos = new FileOutputStream(uploadDir + "orig/"
					+ fileName);
			int bytesRead;
			byte[] buffer = new byte[8192];

			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			bos.close();
			stream.close();

			System.setProperty("jmagick.systemclassloader", "no");
			ImageInfo info = new ImageInfo(uploadDir + "orig/" + fileName);
			MagickImage image = new MagickImage(info);
			MagickImage resizeImg = ImageUtil.resize(image, w, h, type);
			resizeImg.setFileName(uploadDir + fileName);
			resizeImg.writeImage(info);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 上传图片
	 * 
	 * @param path
	 *            保存路径
	 * @param fileName
	 *            保存名称
	 * @param img
	 *            图片
	 * @param w
	 *            缩略图宽度
	 * @param h
	 *            缩略图高度
	 * @param type
	 * @throws IOException
	 * @throws MagickException
	 */
	public static void uploadImage(String path, String fileName, File img,
			int w, int h, String type) throws IOException, MagickException {
		String uploadDir = ServletActionContext.getServletContext()
				.getRealPath("/")
				+ "/" + path;
		InputStream stream = new FileInputStream(img);
		File file = new File(uploadDir + "orig/");
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		OutputStream bos = new FileOutputStream(uploadDir + "orig/" + fileName);
		int bytesRead;
		byte[] buffer = new byte[8192];

		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}

		bos.close();
		stream.close();

		/* 使用了jmagick 配置麻烦  此段注释为Eshow原始代码*/
//		System.setProperty("jmagick.systemclassloader","no");
//		ImageInfo info = new ImageInfo(uploadDir + "orig/" + fileName);
//		MagickImage image = new MagickImage(info);
//		MagickImage resizeImg = ImageUtil.resize(image, w, h, type);
//		resizeImg.setFileName(uploadDir + fileName);
//		resizeImg.writeImage(info);
		
		
		
		

	}
	
//	public static void uploadImage(String path, String fileName, File img,
//			int w, int h, String type) throws IOException, MagickException {
//		String uploadDir = ServletActionContext.getServletContext()
//				.getRealPath("/")
//				+ "/" + path;
//		InputStream stream = new FileInputStream(img);
//		File file = new File(uploadDir + "orig/");
//		if (!file.exists() || !file.isDirectory()) {
//			file.mkdirs();
//		}
//		OutputStream bos = new FileOutputStream(uploadDir + "orig/" + fileName);
//		int bytesRead;
//		byte[] buffer = new byte[8192];
//
//		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
//			bos.write(buffer, 0, bytesRead);
//		}
//
//		bos.close();
//		stream.close();
//
//		/* 使用了jmagick 配置麻烦  此段注释为Eshow原始代码*/
//		System.setProperty("jmagick.systemclassloader","no");
//		ImageInfo info = new ImageInfo(uploadDir + "orig/" + fileName);
//		MagickImage image = new MagickImage(info);
//		MagickImage resizeImg = ImageUtil.resize(image, w, h, type);
//		resizeImg.setFileName(uploadDir + fileName);
//		resizeImg.writeImage(info);
//		
//		
//		
//		
//
//	}

	/**
	 * 读取指定文件生成缩略图
	 * 
	 * @param path
	 *            新路径
	 * @param filePath
	 *            原文件路径
	 * @param fileName
	 *            文件名称
	 * @param w
	 * @param h
	 * @param type
	 * @throws MagickException
	 * @throws IOException 
	 */
	public static void resizeImage(String path, String filePath,
			String fileName, int w, int h, String type) throws MagickException, IOException {
		
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ "/";
		
		/**/
		File orgfile = new File(realPath+filePath+fileName);
		InputStream stream = new FileInputStream(orgfile);//读取ORGI下单图片 弄成流
		
		File file = new File(realPath + path);
		//File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		
		
		OutputStream bos = new FileOutputStream(realPath+path + fileName); //新图片流
		int bytesRead;
		byte[] buffer = new byte[8192];

		while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}

		bos.close();
		stream.close();
		
		/**/
		
		
		
//		String realPath = ServletActionContext.getServletContext().getRealPath(
//				"/")
//				+ "/";
//		File file = new File(realPath + path);
//		if (!file.exists() || !file.isDirectory()) {
//			file.mkdirs();
//		}
		/*
		System.setProperty("jmagick.systemclassloader", "no");
		ImageInfo info = new ImageInfo(realPath + filePath + fileName);
		MagickImage image = new MagickImage(info);
		MagickImage resizeImg = ImageUtil.resize(image, w, h, type);
		resizeImg.setFileName(realPath + path + fileName);
		resizeImg.writeImage(info);
		*/
		
//		File uploadedFile = new File(realPath + path, fileName);

//		try {
//			InputStream inputStream = new FileInputStream(uploadedFile);
//			//FileCopyUtils.copy(inputStream, new FileOutputStream(uploadedFile));
//			FileCopyUtils.copy(inputStream, new FileOutputStream(uploadedFile));
//		} catch (FileNotFoundException e) {
////			uploadError("上传文件出错！");
////			ExceptionUtil.getExceptionMessage(e);
//			e.printStackTrace();
//			return;
//		} catch (IOException e) {
////			uploadError("上传文件出错！");
////			ExceptionUtil.getExceptionMessage(e);
//			//e.printStackTrace();
////			return;
//		}
	}

	/**
	 * 根据原文件生成新缩略图
	 * 
	 * @param origImg
	 *            新路径
	 * @param imgPath
	 *            新文件路径
	 * @param imgName
	 *            新文件名称
	 * @param w
	 * @param h
	 * @param type
	 * @throws MagickException
	 */
	public static void thumbImage(String origImg, String imgPath,
			String imgName, int w, int h, String type) throws MagickException {
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/")
				+ "/";
		File file = new File(realPath + imgPath);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		System.setProperty("jmagick.systemclassloader", "no");
		ImageInfo info = new ImageInfo(realPath + origImg);
		MagickImage image = new MagickImage(info);
		MagickImage resizeImg = ImageUtil.resize(image, w, h, type);
		resizeImg.setFileName(realPath + imgPath + imgName);
		resizeImg.writeImage(info);
	}

	public static MagickImage resize(MagickImage image, int w, int h,
			String type) throws MagickException {
		Dimension dim = image.getDimension();
		int width = (int) dim.getWidth();
		int height = (int) dim.getHeight();
		// if (width / height > 2.5) {
		// Rectangle rect = new Rectangle();
		// if (height > h) {
		// rect.height = h;
		// rect.y = (height - h) / 2;
		// }
		// else {
		// rect.height = height;
		// rect.y = 0;
		// }
		// rect.width = w;
		// rect.x = (width - w) / 2;
		// return image.cropImage(rect);
		// }
		// else if (height / width > 2.5) {
		// Rectangle rect = new Rectangle();
		// if (width > w) {
		// rect.width = w;
		// rect.x = (width - w) / 2;
		// }
		// else {
		// rect.width = width;
		// rect.x = 0;
		// }
		// rect.height = h;
		// rect.y = (height - h) / 2;
		// return image.cropImage(rect);
		// }
		// else {
		if (type.equals("zoom")) {

			int newh = h;
			int neww = w;
			// 如果压缩宽度大于实际宽度
			if (w > width) {
				neww = width;
				// 如果压缩高度大于实际高度
				if (h > height) {
					newh = height;
				} else {
					newh = h;
					neww = width * h / height;
				}
			} else {
				neww = w;
				// 如果压缩高度大于实际高度
				if (h > height) {
					newh = height * w / width;
				} else {
					if (width > height) {
						neww = w;
						newh = height * w / width;
					} else {
						newh = h;
						neww = width * h / height;
					}
				}
			}
			return image.scaleImage(neww, newh);
		} else if (type.equals("crop")) {
			Rectangle rect = new Rectangle();
			rect.width = w;
			rect.height = h;
			// 取中间
			rect.x = w / 4;
			rect.y = h / 4;
			return image.cropImage(rect);
		} else {
			return image.scaleImage(w, h);
		}
		// }
	}
}
