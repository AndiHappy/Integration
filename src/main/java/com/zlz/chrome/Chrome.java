//package com.zlz.chrome;
//
//import com.zlz.ebook.FileUtil;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.PageLoadStrategy;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import java.io.File;
//
//public class Chrome {
//
//	public static void main(String[] args) throws Exception {
//		System.setProperty("webdriver.chrome.driver", "/Users/zhailzh/project/riskproject/integration/src/main/resources/chromedriver");
//		Chrome rome = new Chrome();
//		rome.testGoogleSearch();
//	}
//
//	public void testGoogleSearch() throws Exception {
//		String fileName = "2.txt";
//		File file = new File(fileName);
//		if(file.exists()) {
//			file.delete();
//		}
//
//		file.createNewFile();
//
//
//		// Optional, if not specified, WebDriver will search your path for chromedriver.
//		ChromeOptions options = new ChromeOptions();
////		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
//		options.addArguments("--headless");
////		options.addArguments("--no-sandbox");//  # 取消沙盒模式
//		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//
//		ChromeDriver driver = new ChromeDriver(options);
//
//		driver.get("https://www.yanqbook.com/5/5030/");
//
//
//		// 首先是拿到content
//		WebElement elenet = driver.findElementById("_17mb_content");
//
//		System.out.println(elenet.toString());
//		System.out.println(elenet.getText());
//		String links = driver.findElementByLinkText("下一页").getAttribute("href");
//		if(links!= null && links.length() > 0){
//			while(true){
//				boolean contine = false;
//				try{
//					WebElement value = driver.findElementByLinkText("下一页");
//					System.out.println(value.isEnabled());
//					driver.findElementByLinkText("下一页").click();
//					boolean flag = true;
//					while(flag){
//						elenet = driver.findElementById("_17mb_content");
//						String text = elenet.getText();
//						if(text.startsWith("正在加载")){
//							Thread.sleep(50);
//						}else {
//							flag=false;
//							FileUtils.write(file, text, "UTF-8", true);
//						}
//					}
//
//					System.out.println("加载了："+ driver.getTitle());
//				}catch (Exception e){
//					contine = true;
//				}
//
//				if(contine){
//					try{
//						WebElement value = driver.findElementByLinkText("下一章");
//						System.out.println(value.isEnabled());
//						driver.findElementByLinkText("下一章").click();
//						elenet = driver.findElementById("_17mb_content");
//						boolean flag = true;
//						while(flag){
//							elenet = driver.findElementById("_17mb_content");
//							String text = elenet.getText();
//							if(text.startsWith("正在加载")){
//								Thread.sleep(50);
//							}else {
//								flag=false;
//								FileUtils.write(file, text, "UTF-8", true);
//								FileUtils.write(file, "\n\n", "UTF-8", true);
//							}
//						}
//
//						System.out.println("加载了："+ driver.getTitle());
//					}catch (Exception e){
//						//
//					}
//				}
//			}
//		}
//
//
//		driver.quit();
//	}
//
//}
