/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.constant.services;

import com.yagipharmacy.DAO.ProductDAO;
import com.yagipharmacy.DAO.ProductUnitDAO;
import com.yagipharmacy.DAO.StockDAO;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.Stock;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author admin
 */
public class CalculatorService {

    StockDAO constantStockDAO = new StockDAO();
    ProductUnitDAO constantProductUnitDAO = new ProductUnitDAO();

    public static Long parseLong(String n) {
        if (n == null) {
            n = "0";
        }
        try {
            Long i = Long.parseLong(n.trim());
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static Double parseDouble(String n) {
        if (n == null) {
            n = "0";
        }
        try {
            Double i = Double.parseDouble(n.trim());
            return i;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public static boolean calcStock(String productId, String unitId) throws SQLException, ClassNotFoundException {
        StockDAO stockDAO = new StockDAO();
        ProductDAO productDAO = new ProductDAO();
        Product findingProduct = productDAO.getById(productId);
        if (findingProduct.getProductId() == 0L) {
            return false;
        }
        int listSize =findingProduct.getProductUnits().size();
        int [] stones = new int[listSize];
        int [] conversions = new int[listSize-1];
        for(int i=0;i<listSize;i++){
            stones[i] = stockDAO.getNotOutOfDateSumQuantityOfProductUnit(productId, findingProduct.getProductUnits().get(i).getUnitId()+"").intValue();
        }
        return false;
    }

    public static int[][] getStoneConversion(int[] stones, int[] conversions, int targetType, int targetCount) {
        int n = stones.length;
        int[] stonesTaken = new int[n];  // Số viên đã lấy từ mỗi loại
        int[] stonesAdded = new int[n];  // Số viên được cộng thêm từ việc đập đá lớn

        // Xử lý từ loại đá targetType trở xuống loại nhỏ nhất
        for (int i = targetType; i >= 0 && targetCount > 0; i--) {
            if (stones[i] >= targetCount) {
                stonesTaken[i] = targetCount;
                stones[i] -= targetCount;
                targetCount = 0;
            } else {
                stonesTaken[i] = stones[i];
                targetCount -= stones[i];
                stones[i] = 0;
            }
        }

        // Nếu chưa đủ, tiếp tục từ các loại đá lớn hơn targetType
        for (int i = targetType + 1; i < n && targetCount > 0; i++) {
            int stonesNeeded = (int) Math.ceil((double) targetCount / conversions[i - 1]);

            if (stones[i] >= stonesNeeded) {
                stonesTaken[i] = stonesNeeded;
                stones[i] -= stonesNeeded;
                stonesAdded[i - 1] += stonesNeeded * conversions[i - 1];
                targetCount = 0;
            } else {
                stonesTaken[i] = stones[i];
                stonesAdded[i - 1] += stones[i] * conversions[i - 1];
                targetCount -= stones[i] * conversions[i - 1];
                stones[i] = 0;
            }
        }

        return new int[][]{stonesTaken, stonesAdded};
    }

    public static boolean checkEnough(ProductUnit productUnit, Long quantity) {
        ProductUnit temp = productUnit;
        StockDAO stockDAO = new StockDAO();
        boolean enough = false;
        Long convertTotalQuan = 0L;
        try {
            while (temp != null && temp.getProductUnitId() != 0) {
                Long total_quantity = stockDAO.getNotOutOfDateSumQuantityOfProductUnit(productUnit.getProductId() + "", productUnit.getUnitId() + "") * convertProductUnit(temp, productUnit);
                convertTotalQuan += total_quantity;
                temp = temp.getParentProductUnit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertTotalQuan >= quantity;
    }

//    public static boolean checkStillInStock(ProductUnit productUnit) {
//        ProductUnit temp = productUnit;
//        StockDAO stockDAO = new StockDAO();
//        boolean stillInStock = false;
//        try {
//            while (temp != null && temp.getProductUnitId() != 0) {
//                Long total_quantity = stockDAO.getNotOutOfDateSumQuantityOfProductUnit(productUnit.getProductId() + "", productUnit.getUnitId() + "");
//                if (total_quantity > 0) {
//                    stillInStock = true;
//                    break;
//                }
//                temp = temp.getParentProductUnit();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return stillInStock;
//    }
    public static Long checkHigherLv(ProductUnit pu_1, ProductUnit pu_2) {
        ProductUnit tempPU = pu_2;
        int count = 0;
        while (!(tempPU.getParentProductUnit() == null || tempPU.getParentProductUnit().getProductUnitId() == 0L)) {
            if (pu_1.getProductUnitId() == tempPU.getProductUnitId()) {
                if (count == 0) {
                    return 0L;
                } else {
                    return 1L;
                }
            }
            count++;
        }

        return -1L;
    }

    public static Long convertProductUnit(ProductUnit pu_1, ProductUnit pu_2) {
        ProductUnit higherLV = pu_1;
        ProductUnit lowerLV = pu_2;
        ProductUnit tempPU = pu_2;
        Long tempNum = 1L;
        boolean check = false;
        while (!(tempPU.getParentProductUnit() == null || tempPU.getParentProductUnit().getProductUnitId() == 0L)) {
            if (higherLV.getProductUnitId() == tempPU.getProductUnitId()) {
                check = true;
                break;
            }
            tempPU = tempPU.getParentProductUnit();
            tempNum = tempNum * tempPU.getQuantityPerUnit();
        }

        if (check) {
            return tempNum;
        }
        return 0L;
    }

    public static void bubbleSortStocksByExpDate(List<Stock> stocks) {
        int n = stocks.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Date expDate1 = stocks.get(j).getExpDate();
                Date expDate2 = stocks.get(j + 1).getExpDate();

                // So sánh và hoán đổi nếu expDate1 lớn hơn expDate2
                if (expDate1.after(expDate2)) {
                    Stock temp = stocks.get(j);
                    stocks.set(j, stocks.get(j + 1));
                    stocks.set(j + 1, temp);
                }
            }
        }
    }
    
    public static String formatPrice(Double price) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en", "US"));
        return formatter.format(price);
    }

}
