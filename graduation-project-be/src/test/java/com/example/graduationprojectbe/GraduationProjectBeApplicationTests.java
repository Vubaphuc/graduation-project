package com.example.graduationprojectbe;

import com.example.graduationprojectbe.config.GenerateCode;
import com.example.graduationprojectbe.constants.Status;
import com.example.graduationprojectbe.entity.*;
import com.example.graduationprojectbe.repository.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
class GraduationProjectBeApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenerateCode generateCode;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private ComponentsRepository componentsRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    OrderMaterialRepository orderMaterialRepository;




    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "NHANVIENLETAN"),
                new Role(null, "NHANVIENKHO"),
                new Role(null, "NHANVIENSUACHUA"),
                new Role(null, "NHANVIENBAOHANH"),
                new Role(null, "ADMIN")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users () {

        Role khoRole = roleRepository.findByName("NHANVIENKHO").orElse(null);
        Role baoHanhRole = roleRepository.findByName("NHANVIENBAOHANH").orElse(null);
        Role suaChuaRole = roleRepository.findByName("NHANVIENSUACHUA").orElse(null);
        Role leTanRole = roleRepository.findByName("NHANVIENLETAN").orElse(null);
        Role ADMINRole = roleRepository.findByName("ADMIN").orElse(null);


        assert suaChuaRole != null;
        assert ADMINRole != null;
        assert khoRole != null;
        assert leTanRole != null;
        assert baoHanhRole != null;
        List<User> users = List.of(
                new User(null, generateCode.generateCode(),"Diệu Linh", "linh@gmail.com",passwordEncoder.encode("111"),"0968616076","Hà Nội",true,null,List.of(khoRole)),
                new User(null, generateCode.generateCode(),"Bá Hậu", "hau@gmail.com",passwordEncoder.encode("111"),"0968616076","Hà Nội",true,null,List.of(baoHanhRole)),
                new User(null, generateCode.generateCode(),"Bá Phúc", "phuc@gmail.com",passwordEncoder.encode("111"),"0968616076","Hà Nội",true,null,List.of(ADMINRole)),
                new User(null, generateCode.generateCode(),"Giáp Nhàn", "nhan@gmail.com",passwordEncoder.encode("111"),"0968616076","Hà Nội",true,null,List.of(leTanRole)),
                new User(null, generateCode.generateCode(),"Đăng Quang", "quang@gmail.com",passwordEncoder.encode("111"),"0968616076","Hà Nội",true,null,List.of(suaChuaRole)),
                new User(null, generateCode.generateCode(),"Phùng Văn Tài", "tai@gmail.com",passwordEncoder.encode("111"),"0968616072","Hà Nội",true,null,List.of(suaChuaRole))
        );

        userRepository.saveAll(users);

    }



    @Test
    void save_vendor() {

        User user = userRepository.findById(1).orElse(null);

        List<Vendor> vendors = List.of(
                new Vendor(null,"QUALCOMM",LocalDateTime.now(),null,true,user),
                new Vendor(null,"MEDIAMART",LocalDateTime.now(),null,true,user),
                new Vendor(null,"SHANNON",LocalDateTime.now(),null,true,user),
                new Vendor(null,"SAMSUNG",LocalDateTime.now(),null,true,user)
        );

        vendorRepository.saveAll(vendors);

    }

    @Test
    void save_linhKien () {

        User user = userRepository.findById(1).orElse(null);

        List<Components> linhKiens = List.of(
                new Components(null,"LCD",12,LocalDateTime.now(),null,true,user),
                new Components(null,"BackGlass",12,LocalDateTime.now(),null,true,user),
                new Components(null,"Rear",12,LocalDateTime.now(),null,true,user),
                new Components(null,"PIN",12,LocalDateTime.now(),null,true,user),
                new Components(null,"FRONT",12,LocalDateTime.now(),null,true,user),
                new Components(null,"PBA",12,LocalDateTime.now(),null,true,user),
                new Components(null,"Sub PBA",12,LocalDateTime.now(),null,true,user),
                new Components(null,"SPK",12,LocalDateTime.now(),null,true,user),
                new Components(null,"Camera Wide",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Camera Ultra Wide",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Camera Tele",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Camera Bokeh",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Front Camera",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Camera Macro",9,LocalDateTime.now(),null,true,user),
                new Components(null,"Motor",16,LocalDateTime.now(),null,true,user),
                new Components(null,"Ant 5G",16,LocalDateTime.now(),null,true,user),
                new Components(null,"Power Key",16,LocalDateTime.now(),null,true,user),
                new Components(null,"Wifi",18,LocalDateTime.now(),null,true,user),
                new Components(null,"USB-C",18,LocalDateTime.now(),null,true,user),
                new Components(null,"GPS",18,LocalDateTime.now(),null,true,user)
        );

        componentsRepository.saveAll(linhKiens);
    }

    @Test
    void save_customer() {
        User letan = userRepository.findUsersByEmail("nhan@gmail.com").orElse(null);

        List<Customer> customers = List.of(
                new Customer(null,"Hoàng Văn Đại","0961616223","dai@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn Nghĩa","0962616323","nghia@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn quang","0963616423","quang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn Văn","0964616523","van@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị thúy","0965616623","thuy@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị vân","0966616723","vanht@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị thuyên","0967616823","thuyen@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn phúc","0968616923","phuc@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn hau","0969616023","hau@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn đăng","0960616123","dang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn quân","0961616523","quan@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn chiến","0962616123","chien@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn tuyển","0963616123","tuyen@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn anh","0964616123","anh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn trọng anh","0965616123","anhhvt@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị kiều","0966616123","kieu@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị trang","0967616123","trang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị linh","0968616123","linh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị kim anh","0969616123","anhhtk@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị ánh dương","0968116123","duong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị tuyết","0968216123","tuyết@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn đức","0968316123","duc@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn luân","0968416123","luan@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn thạo","0968516123","thao@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn hiếu","0968616123","hieu@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn dinh","0968716123","dinh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn sơn","0968816123","son@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn hoàng","0968916123","hoang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn bình","0968016123","binh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn kiên","0968626123","kien@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn độ","0968636123","do@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn cường","0968646123","cuong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn phương","0968656123","phuong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn giang","0968666123","giang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn chấp","0968676123","chap@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn kỳ","0968686123","ky@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn hưng","0968696123","hung@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn duy","0968606123","duy@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn ngọc","0968616121","ngoc@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn nam","0968616122","nam@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng thị nghị","0968616123","nghi@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn tùng","0968616124","tung@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn thắng","0968616125","thang@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn Bảo","0968616126","bao@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn ân","0968616127","an@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn dũng","0968616128","dung@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn nguyên","0968616129","nguyen@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn thiện","0968616120","thien@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn triết","0918616123","triet@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn trung","0928616123","trung@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn chung","0938616123","chung@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn trường","0948616123","truong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn tuấn","0958616123","tuan@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn vinh","0968616123","vinh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn phát","0978616123","phat@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn phong","0988616123","phong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn kiệt","0998616123","kiet@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn minh","0998616123","minh@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn khoa","0768616123","khoa@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan),
                new Customer(null,"Hoàng Văn công","0868616123","cong@gmail.com","Thành Phố Hà Nội",LocalDateTime.now(),true,letan)
        );

        customerRepository.saveAll(customers);
    }


    @Test
    void save_material () {

        List<Vendor> vendors = vendorRepository.findAll();
        List<Components> components = componentsRepository.findAll();

        User kho = userRepository.findUsersByEmail("linh@gmail.com").orElse(null);

        Random rd = new Random();


        List<Material> materials = List.of(
                new Material(null, generateCode.generateCode(), "G990F/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(0),kho),
                new Material(null, generateCode.generateCode(), "N770F/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(1),kho),
                new Material(null, generateCode.generateCode(), "F936B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(2),kho),
                new Material(null, generateCode.generateCode(), "F926B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(3),kho),
                new Material(null, generateCode.generateCode(), "S901B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(4),kho),
                new Material(null, generateCode.generateCode(), "S911B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(5),kho),
                new Material(null, generateCode.generateCode(), "G781B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(6),kho),
                new Material(null, generateCode.generateCode(), "A536B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(7),kho),
                new Material(null, generateCode.generateCode(), "A325F/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(8),kho),
                new Material(null, generateCode.generateCode(), "A326B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(9),kho),
                new Material(null, generateCode.generateCode(), "A102U/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(10),kho),
                new Material(null, generateCode.generateCode(), "S908B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(11),kho),
                new Material(null, generateCode.generateCode(), "S906B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(12),kho),
                new Material(null, generateCode.generateCode(), "S918B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(13),kho),
                new Material(null, generateCode.generateCode(), "S916B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(14),kho),
                new Material(null, generateCode.generateCode(), "A730B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(15),kho),
                new Material(null, generateCode.generateCode(), "A736B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(16),kho),
                new Material(null, generateCode.generateCode(), "A336B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(17),kho),
                new Material(null, generateCode.generateCode(), "M336B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(18),kho),
                new Material(null, generateCode.generateCode(), "M236B/128D",100,0,100,LocalDateTime.now(),null,100000,true,vendors.get(rd.nextInt(vendors.size())),components.get(19),kho)
        );

        materialRepository.saveAll(materials);
    }

    @Test
    void save_product () {

        Random rd = new Random();

        List<String> models = Arrays.asList(
                "G990F/128D", "N770F/128D", "F936B/128D", "F926B/128D", "S901B/128D", "S911B/128D", "G781B/128D",
                "A536B/128D", "A325F/128D", "A326B/128D", "A102U/128D", "S908B/128D", "S906B/128D", "S918B/128D",
                "S916B/128D", "A730B/128D", "A736B/128D", "A336B/128D", "M336B/128D", "M236B/128D"
        );
        List<String> phoneCompanies = Arrays.asList(
                "SamSung", "Apple", "Huawei", "Xiaomi", "OPPO"
        );
        List<String> defectName = Arrays.asList(
                "Tối LCD", "Không Rung", "Không Lên Nguồn", "Không vào được camera sau", "Không vào được camera trước", "Không sạc được pin"
        );

        List<Customer> customers = customerRepository.findAll();
        // i đã tới 59
        for (int i = 0; i < customers.size(); i++) {
            Product product = Product.builder()
                    .phoneCompany(phoneCompanies.get(rd.nextInt(phoneCompanies.size())))
                    .nameModel(models.get(rd.nextInt(models.size())))
                    .customer(customers.get(i))
                    .receptionists(customers.get(i).getReceptionists())
                    .defectName(defectName.get(rd.nextInt(defectName.size())))
                    .ime(generateRandomIMEI(rd))
                    .inputDate(LocalDateTime.now())
                    .price(2000000)
                    .delete(true)
                    .repair(false)
                    .status(Status.WAITING_FOR_REPAIR)
                    .build();
            productRepository.save(product);
        }

    }

    // Hàm để tạo IMEI ngẫu nhiên có 12 chữ số
    public static String generateRandomIMEI(Random random) {
        StringBuilder sb = new StringBuilder();

        // Thêm 12 chữ số ngẫu nhiên
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
