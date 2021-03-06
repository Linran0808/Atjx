package com.atjx.controller;

import com.atjx.mapper.*;
import com.atjx.model.*;
import com.atjx.util.Constant;
import com.atjx.util.DateUtil;
import com.atjx.util.ExcelUtil;
import com.atjx.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

//import com.mongodb.gridfs.GridFSDBFile;


/**
 * 商品管理
 */
@Controller
public class ItemController {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ItemCategoryMapper itemCategoryMapper;

    @Resource
    private ReItemMapper reItemMapper;

    @Resource
    private SpecificationMapper specificationMapper;
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private TixianMapper tixianMapper;

    public static final String ROOT = "src/main/resources/static/img/item/";

//    MongoUtil mongoUtil = new MongoUtil();

    private final ResourceLoader resourceLoader;

    @Autowired
    public ItemController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    List<Item> itemList;

    File getFile = null;

    @RequestMapping("/user/itemManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemManage(Item item, @PathVariable Integer pageCurrent,
                             @PathVariable Integer pageSize,
                             @PathVariable Integer pageCount,
                             Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        int rows = itemMapper.count(item);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        itemList = itemMapper.list(item);
        for (Item i : itemList) {
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minPrice = item.getMinPrice();
        Integer maxPrice = item.getMaxPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/itemManage";
    }

    @RequestMapping("/user/itemAddress_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemAddManage(Item item, @PathVariable Integer pageCurrent,
                             @PathVariable Integer pageSize,
                             @PathVariable Integer pageCount,
                             Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        int rows = itemMapper.count(item);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        itemList = itemMapper.list(item);
        for (Item i : itemList) {
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minPrice = item.getMinPrice();
        Integer maxPrice = item.getMaxPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/itemAddress";
    }
    @RequestMapping("/user/picManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String PicManage(Item item, @PathVariable Integer pageCurrent,
                                @PathVariable Integer pageSize,
                                @PathVariable Integer pageCount,
                                Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        int rows = itemMapper.count(item);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        itemList = itemMapper.list(item);
        for (Item i : itemList) {
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minPrice = item.getMinPrice();
        Integer maxPrice = item.getMaxPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/picManage";
    }

    @RequestMapping("/user/itemSpeManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemSpeManage(Item item, @PathVariable Integer pageCurrent,
                                @PathVariable Integer pageSize,
                                @PathVariable Integer pageCount,
                                Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        int rows = itemMapper.count(item);
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        itemList = itemMapper.list(item);
        for (Item i : itemList) {
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minPrice = item.getMinPrice();
        Integer maxPrice = item.getMaxPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() + "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice, pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/speManage";
    }

    @RequestMapping("/user/TixianManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String TixianManage(Tixian tixian, @PathVariable Integer pageCurrent,
                                @PathVariable Integer pageSize,
                                @PathVariable Integer pageCount,
                                Model model) {
        if (pageSize == 0) pageSize = 50;
        if (pageCurrent == 0) pageCurrent = 1;

        int rows = tixianMapper.count();
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        tixian.setStart((pageCurrent - 1) * pageSize);
        tixian.setEnd(pageSize);
        List<Tixian> tixians = tixianMapper.TIXIAN_LIST();
        for (Tixian i : tixians) {
            i.setCreateStr(DateUtil.getDateStr(i.getCreatTime()));
        }
        model.addAttribute("tixianList", tixians);
        String pageHTML = PageUtil.getPageContent("TixianManage_{pageCurrent}_{pageSize}_{pageCount}?", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", tixian);
        return "item/TixianList";
    }

    @RequestMapping("/user/download1")
    public void postItemExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //导出excel
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("id", "商品id");
        fieldMap.put("title", "商品标题");
        fieldMap.put("sell_Point", "商品卖点");
        fieldMap.put("price", "商品价格");
        fieldMap.put("num", "库存数量");
        fieldMap.put("image", "商品图片");
        fieldMap.put("cid", "所属类目，叶子类目");
        fieldMap.put("status", "商品状态，1-正常，2-下架，3-删除");
        fieldMap.put("created", "创建时间");
        fieldMap.put("updated", "更新时间");
        String sheetName = "商品管理报表";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=ItemManage.xls");//默认Excel名称
        response.flushBuffer();
        OutputStream fos = response.getOutputStream();
        try {
            ExcelUtil.listToExcel(itemList, fieldMap, sheetName, fos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    String imageName = null;

    @RequestMapping("/user/NewItem")
    public String NewItem(){

        return "item/NewItemEdit";
    }
    @RequestMapping("/NewItem")
    public String NewItemEdit(Item item,HttpServletRequest request){
        String notes = request.getParameter("sell_point");

        String html= HtmlUtils.htmlEscape(notes);
        item.setSell_Point(html);
        itemMapper.insert(item);
        return "redirect:/user/itemManage_0_0_0";
    }

    @GetMapping("/user/itemEdit")
    public String itemEditGet(Model model, Item item) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);

        String html=item.getSell_Point();
        String unhtml=HtmlUtils.htmlUnescape(html);
        item.setSell_Point(unhtml);
        if (item.getId() != 0) {
            Item item1 = itemMapper.findById(item);
            itemMapper.update(item1);
            model.addAttribute("item", item1);
        }
        return "item/itemEdit";
    }

    @PostMapping("/user/itemEdit")
    public String itemEditPost(HttpServletRequest request, Item item) throws IOException {
        //生成日期
        Date date=new Date();
        //设置商品更新时间
        item.setUpdated(date);
        item.setBarcode("");
        //获取前端商品详情富文本
        String notes = request.getParameter("sell_point");
        String html= HtmlUtils.htmlEscape(notes);
        item.setSell_Point(html);
        if(item.getId() == 0){
            itemMapper.insert(item);
        }else{
            itemMapper.update(item);
        }

        return "redirect:itemManage_0_0_0";


    }
    @GetMapping(value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile() {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, imageName).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @ResponseBody
    @PostMapping("/user/itemEditState")
    public ResObject<Object> itemEditState(Item item1) {
        itemMapper.delete(item1);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }

    @ResponseBody
    @RequestMapping(value="/uploadImg",method={RequestMethod.POST})
    public  String uploadImg(HttpServletRequest request,@RequestParam(value="file")MultipartFile file,Item item,Model model){

        Date date = new Date();
        String id1=request.getParameter("id1");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(date) + "_" + new Random().nextInt(1000) ;
        String originalFileName=file.getOriginalFilename();

        String extension=originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());

        int id= Integer.parseInt(id1);
        item.setId(id);

        String name="P"+id1+"_"+newFileName;
        String dt=new SimpleDateFormat("/yyyyMM/").format(date);
        File path= new File("/");
        File upload = new File(path.getAbsolutePath(),"static/upload/images"+dt);

        String webUrl="/static/upload/images"+dt+name+extension;
        item=itemMapper.findById(item);
        item.setImage(webUrl);
//        webUrl=webUrl.replaceAll("http://", "").replaceAll("/uploadImg/WEB-INF", "");
        webUrl="{\"url\":\""+webUrl+"\",\"path\":\"/upload"+dt+extension+"\"}";

        File dir=new File(String.valueOf(upload));
        //上传图片
        try {
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(new File(upload+"/"+name+extension));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

            itemMapper.update(item);

            return webUrl;
    }

    @ResponseBody
    @RequestMapping(value="/newImg",method={RequestMethod.POST})
    public  String NewImage(HttpServletRequest request,@RequestParam(value="file")MultipartFile file,Item item,Model model) throws FileNotFoundException {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(date) + "_" + new Random().nextInt(1000) ;
        String originalFileName=file.getOriginalFilename();
        String extension=originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
        int id=itemMapper.findMaxId();
        int a=1;
        id+=a;
        String name="P"+id+"_"+newFileName;
        String dt=new SimpleDateFormat("/yyyyMM/").format(date);
//        String path=ClassUtils.getDefaultClassLoader().getResource("").getPath();
        File path = new File("/");
        File upload = new File(path.getAbsolutePath(),"static/upload/images"+dt);

        String webUrl="/static/upload/images"+dt+name+extension;

        webUrl=webUrl.replaceAll("http://", "").replaceAll("/uploadImg/WEB-INF", "");
        webUrl="{\"url\":\""+webUrl+"\",\"path\":\"/upload/"+dt+extension+"\"}";
        File dir=new File(String.valueOf(upload));
        try {
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(new File(upload+"/"+name+extension));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return webUrl;
    }

    @RequestMapping("/user/addEdit")
    public String addEdit(Item item,Model model){
        if (item.getId() != 0) {
            Item item1 = itemMapper.findById(item);
            model.addAttribute("item", item1);
            List<Address> list=addressMapper.selectAll(item1.getId());
            model.addAttribute("list", list);
        }
        return "item/addManageEdit";
    }

    @RequestMapping("/user/addPost")
    public String addPost(Item item,Address address,Model model,HttpServletRequest request){
        String id=request.getParameter("item_id");
        address.setItem_id(Integer.parseInt(id));
        addressMapper.insert(address);
        return "redirect:/user/addEdit?id="+id;
    }

    //删除地址
    @RequestMapping("/addDelete")
    public String addDelete(HttpServletRequest request, Address address){
        String add_id=request.getParameter("add_id");
        String id=request.getParameter("id");
        addressMapper.delete(Integer.parseInt(add_id));
        return "redirect:/user/addEdit?id="+id;
    }


    //商品套餐列表
    @RequestMapping("/user/speEdit")
    public String speEdit(Item item,Model model)
    {
        if (item.getId() != 0) {
            Item item1 = itemMapper.findById(item);
            model.addAttribute("item", item1);
            List<Specification> list=specificationMapper.selectAll(item1.getId());
            model.addAttribute("list", list);
        }
        return "item/speEdit";
    }

    //增加或修改套餐信息
    @RequestMapping("/user/spePost")
    public String spePost(HttpServletRequest request, Specification spe){
        String item_id=request.getParameter("item_id");

        spe.setItem_id(Integer.parseInt(item_id));
        if(!item_id.equals("0")){
            specificationMapper.insert(spe);
        }else {
            specificationMapper.update(spe);
        }

        String spe_id=request.getParameter("spe_id");
//        String id=request.getParameter("id");

        return "redirect:/user/speEdit?id="+item_id;
    }
    //删除套餐
    @RequestMapping("/speDelete")
    public String speDelete(HttpServletRequest request, Specification spe){
        String spe_id=request.getParameter("spe_id");
        String id=request.getParameter("id");
        specificationMapper.delete(Integer.parseInt(spe_id));
        return "redirect:/user/speEdit?id="+id;
    }


    //查询套餐
    @RequestMapping("/user/speFind")
    public String speFind(Item item, Model model, Specification spe, HttpServletRequest request)
    {
        String spe_id=request.getParameter("spe_id");
        String id=request.getParameter("id");
        Specification spe1=specificationMapper.find(spe);
        model.addAttribute("spe",spe1);
        return "user/speEdit";
    }


    @RequestMapping("/user/itemPicEdit")
    public String picEdit(Item item,Model model)
    {
        if (item.getId() != 0) {
            Item item1 = itemMapper.findById(item);
            model.addAttribute("item", item1);
        }
        return "item/itemPicEdit";
    }


    @RequestMapping("/user/itemPicPost")
    public String picPost(){
        return "redirect:picManage_0_0_0";
    }

    @RequestMapping("/user/tixian")
    private String tixian(Model model,HttpServletRequest request) {
        try{
            String id= request.getParameter("id");
            Tixian tixian1 = tixianMapper.findByid(Integer.parseInt(id));
            System.out.println(tixian1);
            model.addAttribute("tixian", tixian1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "item/tixianedit";
    }
    //删除提现记录
    @RequestMapping("/tixianDelete")
    public String DeleteTixian(HttpServletRequest request, Specification spe){

        return null;
    }
}
