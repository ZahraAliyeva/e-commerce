package com.company.ecommerce.service;

import com.company.ecommerce.entity.*;
import com.company.ecommerce.repo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private PerProductRepository perProductRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductSizesRepository productSizesRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private BucketRepository bucketRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;


    public Product createProduct(Product product){
        String name=product.getProductName();
        Brand brand = product.getBrand();
        Brand savedBrand = null;
        List<Brand> existingBrands = brandRepository.findByBrandName(brand.getBrandName());
        List<Product> existingProducts = productRepo.findByProductName(name);
        if (existingBrands.size() > 0) {
            savedBrand = existingBrands.get(0);
        } else {
            savedBrand = entityManager.merge(brand);
        }
        product.setBrand(savedBrand);

        Sub_category sub_category = product.getSub_categories();
        Sub_category savedSub_category = null;
        Gender existingGender = null;
        List<Gender> existingGenders = new ArrayList<>();
        List<Sub_category> existingSub_category = subCategoryRepository.findByName(sub_category.getName());
        if (existingSub_category.size() > 0) {
            for (Sub_category existingSub : existingSub_category) {
                if (existingSub.getId() == sub_category.getId()) {
                    savedSub_category = existingSub;
                    break;
                }
            }
            if (savedSub_category == null) {
                savedSub_category = existingSub_category.get(0);
            }
            for (Gender gender : sub_category.getGenders()) {
                List<Gender> genders = genderRepository.findByName(gender.getName());
                if (!genders.isEmpty()) {
                    existingGender = genders.get(0);
                    if (!savedSub_category.getGenders().contains(existingGender)) {
                        existingGenders.add(existingGender);
                    }
                } else {
                    existingGender = entityManager.merge(gender);
                    existingGenders.add(existingGender);
                }
            }
            savedSub_category.getGenders().addAll(existingGenders);
            savedSub_category = subCategoryRepository.save(savedSub_category);
            //          savedSub_category.setGenders(existingGenders);
        } else {
            savedSub_category = entityManager.merge(sub_category);
            savedSub_category = subCategoryRepository.save(savedSub_category);
        }
        product.setSub_categories(savedSub_category);


        List<PerProduct> perProducts = product.getProducts();
        Color savedColor = null;
        List<Color> existingColors = null;
        List<Size> existingSizes = new ArrayList<>();
        Size existingSize=null;
        int say=0;
        for (PerProduct perProduct : perProducts) {
            List<ProductSizes> productSizes = perProduct.getProductSizes();
            for (ProductSizes productSize : productSizes) {
                List<Size> size = sizeRepository.findBySizeName(productSize.getSize().getSizeName());
                if (!size.isEmpty()) {
                    existingSize= size.get(0);
                    if (!existingSizes.contains(existingSize)) {
                        existingSizes.add(existingSize);
                    }
                } else {
                    existingSize = entityManager.merge(productSize.getSize());
                    existingSizes.add(existingSize);
                }
                productSize.setSize(existingSize);
                say=say+productSize.getNumbers();
                if (say<= perProduct.getStockNumber()){
                    log.info(say+"+ sgsgsggs "+ perProduct.getStockNumber());
                    productSize.setNumbers(productSize.getNumbers());
                    say=0;
                    log.info(say+"+ sgsgsggs222 "+ perProduct.getStockNumber());

                }else {
                    log.info(say+"+ sgsgsggs "+ perProduct.getStockNumber());
                    productSize.setNumbers(null);//misal ucun bunu set elesin
                    say=0;
                    log.info(say+"+ sgsgsggs222 "+ perProduct.getStockNumber());
                }
                entityManager.merge(productSize);
                productSize.setPerProduct(perProduct); // add reference to perProduct entity
            }
            perProduct.setProductSizes(productSizes);
            Color color = perProduct.getColor();
            existingColors = colorRepository.findByColorName(color.getColorName());
            if (existingColors.size() > 0) {
                savedColor = existingColors.get(0);
            } else {
                savedColor = entityManager.merge(color);
            }
            int ay=0;
            perProduct.setColor(savedColor);
            if(existingProducts.size()>0){
                if(perProducts.stream().anyMatch(x->x.getCode().equals(perProduct.getCode()))){
                    for(PerProduct pp:perProductRepository.findByCode(perProduct.getCode())){
                        ay=ay+pp.getStockNumber();
                    }
                    perProduct.setStockNumber(ay+perProduct.getStockNumber());
                    perProduct.setProduct(product);
                }
            }else {
                perProduct.setStockNumber(perProduct.getStockNumber());
                perProduct.setProduct(product);
            }
        }
        List<Gender> exs=genderRepository.findByName(product.getGender().getName());
        Gender gender=exs.get(0);
        product.setGender(gender);
        product.setProducts(perProducts);
        Product savedProduct = entityManager.merge(product);
        savedProduct=productRepo.save(savedProduct);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product updatedProduct = new Product();
        Optional<Product> savedProduct = productRepo.findById(id);
        updatedProduct = savedProduct.get();
        if(savedProduct.isPresent()){
            BeanUtils.copyProperties(product, updatedProduct/*, Utils.getNullPropertyName(product)*/);
        }
        updatedProduct=productRepo.save(updatedProduct);
        return updatedProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            Product deletedProduct = product.get();
            List<PerProduct> perProducts = deletedProduct.getProducts();
            for (PerProduct perProduct : perProducts) {
                Bucket bb=perProduct.getBucket();
                if (bb!=null){
                    bucketRepository.deleteProductBucketById(perProduct.getId());
                }
                for (Photo p:perProduct.getPhotos()){
                    photoRepository.deletePhotoById(p.getId());
                }
                for (ProductSizes ps:perProduct.getProductSizes()){
                    productSizesRepository.deleteProductSizesById(ps.getId());
                }
                for (Rate r:perProduct.getRate()){
                    rateRepository.deleteRateById(r.getId());
                }
                perProductRepository.deletePerProductById(perProduct.getId());
            }
            productRepo.deleteProductById(deletedProduct.getId());
        }

    }
    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public  Product getProductById(Long id) {
        Product product = new Product();
        Optional<Product> givenProduct = productRepo.findById(id);
        if(givenProduct.isPresent())
            return givenProduct.get();
        return product;
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        List<Product> products = (List<Product>) productRepo.findAllActiveUsersNative(id);
        return products;
    }

    @Override
    public List<Product> getProductsByBrand(Long id) {
        List<Product> products = (List<Product>) productRepo.findProductsByBrandId(id);
        return products;
    }

}
