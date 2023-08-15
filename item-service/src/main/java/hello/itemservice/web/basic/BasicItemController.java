package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    //@RequiredArgsConstructor 이게 있어서 아래 코드 생략가능, 자동으로 만들어줌 !
//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        // Model도 안쓰니까 생략가능
        itemRepository.save(item);
        //model.addAttribute("item", item); // ModelAttribute 쓰니까 생략가능

        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
                                        //@ModelAttribute의 괄호안에 모델이름을 빼면 클래스이름(Item)의 첫글자를
                                        // 소문자로 바꾼 (item) 이 자동으로 들어감! 첫글자만 소문자로 바뀜
        itemRepository.save(item);
        //model.addAttribute("item", item); // ModelAttribute 쓰니까 생략가능
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        //@ModelAttribute 생략가능
        itemRepository.save(item);
        //model.addAttribute("item", item); // ModelAttribute 쓰니까 생략가능
        return "/basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        //@ModelAttribute 생략가능
        itemRepository.save(item);
        //model.addAttribute("item", item); // ModelAttribute 쓰니까 생략가능
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        //model.addAttribute("item", item); // ModelAttribute 쓰니까 생략가능
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.updateItem(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("itemA", 10000, 19));
        itemRepository.save(new Item("itemB", 20000, 13));
    }
}
