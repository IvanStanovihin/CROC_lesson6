import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


class XMLWriter{

        public static void createXMLDocumentForFirstTask(Map<Integer, Optional<AvailabilityCommodity>> listCommodities,
                                                         ParseSellers sellers ){

            // Создаем документ
            Document xmlDoc = new Document();
            // Создаем корневой элемент
            Element root = new Element("Commodities");
            // Добавляем корневой элемент в документ
            xmlDoc.setRootElement(root);

            for(Map.Entry commodity : listCommodities.entrySet()) {
                Optional<AvailabilityCommodity> value = (Optional<AvailabilityCommodity>) commodity.getValue();
                AvailabilityCommodity commodityInfo = value.get();


                // Создаем элемент commodity и добавляем ему атрибут
                Element elementCommodity = new Element("commodity");
                elementCommodity.setAttribute("id", " " + commodity.getKey());

                //создаём элемент в котором указано максимальное колличество товара среди всех продавцов
                Element elementMaxAmountCommodity = new Element("maxAmountCommodity");
                elementMaxAmountCommodity.addContent(" " + commodityInfo.commodityAmount);
                elementCommodity.addContent(elementMaxAmountCommodity);

                //создаём элемент в котором указан продавец, у которого в наличии самое большое колличество текущего товара
                Seller seller = sellers.findSeller(commodityInfo.sellerId);
                Element elementSellerWithMaxAmount = new Element("SellerWithMaxAmountCommodity");
                elementSellerWithMaxAmount.setAttribute("id", "" + seller.getId());
                elementSellerWithMaxAmount.addContent("seller's name: " + seller.getFirstName());
                elementCommodity.addContent(elementSellerWithMaxAmount);

                root.addContent(elementCommodity);
            }

            try {

                Format fmt = Format.getPrettyFormat();

                XMLOutputter serializer = new XMLOutputter(fmt);
                serializer.output(xmlDoc, System.out);
                serializer.output(xmlDoc, new FileOutputStream(new File("src\\outputXMLFiles\\MaxAmountCommodities.xml")));
            }
            catch (IOException e) {
                System.err.println(e);
            }
        }

        public static void createXMLDocumentForSecondTask(Map<LocalDate, Long> distributionByDates){

            // Создаем документ
            Document xmlDoc = new Document();
            // Создаем корневой элемент
            Element root = new Element("Dates");
            // Добавляем корневой элемент в документ
            xmlDoc.setRootElement(root);

            for(Map.Entry<LocalDate, Long> pair : distributionByDates.entrySet()) {
                root.addContent(new Element("date").setAttribute("day", "" + pair.getKey())
                        .addContent(new Element("amountSales").addContent("" + pair.getValue()))
                );
            }

            try {
                Format fmt = Format.getPrettyFormat();
                XMLOutputter serializer = new XMLOutputter(fmt);
                serializer.output(xmlDoc, System.out);
                serializer.output(xmlDoc, new FileOutputStream(new File("src\\outputXMLFiles\\DistributionSales.xml")));
            }
            catch (IOException e) {
                System.err.println(e);
            }
        }

    }
