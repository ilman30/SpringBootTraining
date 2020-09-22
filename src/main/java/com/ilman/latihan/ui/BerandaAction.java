/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilman.latihan.ui;

import com.ilman.latihan.impl.KoneksiJdbc;
import com.ilman.latihan.model.Kabupaten;
import com.ilman.latihan.model.Kecamatan;
import com.ilman.latihan.model.Provinsi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author ilman30
 */
@Controller
public class BerandaAction {
    
    @Autowired
    private KoneksiJdbc koneksiJdbc;
    
    @GetMapping(path = "/")
    public String homeUi(ModelMap model){
        model.put("nama", "Selamat Datang");
        return "home";
    }
    
    @GetMapping(path = "/about")
    public ModelAndView aboutUi(ModelAndView model){
        Map<String,String> map = new HashMap();
        map.put("about", "Ini About");
        return new ModelAndView("about", map);
    }
    
    @GetMapping(path = "/listprovinsi")
    public ModelAndView listProvinsi(ModelAndView model){
        Map<String, List<Provinsi>> map = new HashMap();
        map.put("listProvinsi", koneksiJdbc.getProvinsi());
        return new ModelAndView("listprov", map);
    }
    
    @GetMapping("/addkabupaten")
    public String createProjectFormKab(Model model){
        model.addAttribute("kabupaten", new Kabupaten());
        model.addAttribute("provinsi", koneksiJdbc.getProvinsi());
        return "addkab";
    }
    
    
    @GetMapping(path = "/provinsidetail/{id}")
    public String provinsiDetail(@PathVariable("id") Integer id, Model model ){
//        Map<String, Provinsi> map = new HashMap();
//        map.put("prop", koneksiJdbc.getProvinsiById(id).get());
//        return new ModelAndView("provinsidetail", map);
        model.addAttribute("provinsi", koneksiJdbc.getProvinsiById(id).get());
        return "provinsidetail";
    }
    
    @GetMapping(path = "/listkabupaten")
    public ModelAndView listKabupaten(ModelAndView model){
        Map<String, List<Kabupaten>> map = new HashMap();
        map.put("listKabupaten", koneksiJdbc.getKabupaten());
        return new ModelAndView("listkab", map);
    }
    
    @GetMapping(path = "/kabupatendetail/{id}")
    public String kabupatenDetail(@PathVariable("id") Integer id, Model model){
//        Map<String, Kabupaten> map = new HashMap();
//        map.put("kab", koneksiJdbc.getKabupatenById(id).get());
//        return new ModelAndView("kabupatendetail", map);
         model.addAttribute("kabupaten", koneksiJdbc.getKabupatenById(id).get());
         model.addAttribute("provinsi", koneksiJdbc.getProvinsi());
        return "kabupatendetail";
    }
    
    @GetMapping(path = "/listkabcari")
    public ModelAndView listKabupatenSearch(@RequestParam(name = "nama") Optional<String> namaParam){
        Map<String, List<Kabupaten>> map = new HashMap();
        map.put("listKabupaten", koneksiJdbc.getKabupatenSearch(namaParam));
        return new ModelAndView("listkab", map);
    }
    
    @GetMapping("/addprovinsi")
    public String createProjectForm(Model model){
        model.addAttribute("provinsi", new Provinsi());
        return "addprov";
    }
    
    @PostMapping("/saveprov")
    public String saveprov(@Valid @ModelAttribute Provinsi provinsi, BindingResult result){
        if(result.hasErrors()){
            return "addprov";
        }
        koneksiJdbc.insertProvinsi(provinsi);
        return "redirect:listprovinsi";
    }
    
    @PostMapping("/api/saveprovjson")
    public ResponseEntity <Map<String,Object>> saveprovjson(@RequestBody Provinsi provinsi){
        Map<String, Object> status = new HashMap<>();
        koneksiJdbc.insertProvinsi(provinsi);
        status.put("pesan", "Simpan Berhasil");
        return ResponseEntity.ok().body(status);
    }
    
    @PostMapping("/api/savekabjson")
    public ResponseEntity <Map<String,Object>> savekabjson(@RequestBody Kabupaten kabupaten){
        Map<String, Object> status = new HashMap<>();
        koneksiJdbc.insertKabupaten(kabupaten);
        status.put("pesan", "Simpan Berhasil");
        return ResponseEntity.ok().body(status);
    }
    
    @PostMapping("/updateprov")
    public String updateprov(@Valid @ModelAttribute Provinsi provinsi, BindingResult result){
        if(result.hasErrors()){
            return "provinsidetail";
        }
        koneksiJdbc.updateProvinsi(provinsi);
        return "redirect:listprovinsi";
    }
    
//    @DeleteMapping(path = "/deleteprov/{id}")
//    public ResponseEntity<?> deleteProvinsi(@PathVariable("id") Integer id){
//        try {
//            koneksiJdbc.deleteProvinsi(id);
//            return new ResponseEntity<>(null, HttpStatus.OK);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }
    
//    @PostMapping("/deleteprov")
//    public String deleteprov(@Valid @ModelAttribute Provinsi provinsi, BindingResult result){
//        if(result.hasErrors()){
//            return "listprovinsi";
//        }
//        koneksiJdbc.deleteProvinsi(provinsi);
//        return "redirect:listprovinsi";
//    }
    
    @GetMapping(path = "/deleteprov/{id}")
    public String deleteProvinsi(@PathVariable("id") Integer id, Model model ){
//        Map<String, Provinsi> map = new HashMap();
//        map.put("prop", koneksiJdbc.getProvinsiById(id).get());
//        return new ModelAndView("provinsidetail", map);
        model.addAttribute("provinsi", koneksiJdbc.getProvinsiById(id).get());
        return "listprovinsi";
    }
    
  
    
    @PostMapping("/savekab")
    public String savekab(@Valid @ModelAttribute Kabupaten kabupaten, BindingResult result){
        if(result.hasErrors()){
            return "addkab";
        }
        koneksiJdbc.insertKabupaten(kabupaten);
        return "redirect:listkabupaten";
    }
    
    @PostMapping("/updatekab")
    public String updatekab(@Valid @ModelAttribute Kabupaten kabupaten, BindingResult result){
        if(result.hasErrors()){
            return "kabupatendetail";
        }
        koneksiJdbc.updateKabupaten(kabupaten);
        return "redirect:listkabupaten";
    }
    
    @GetMapping(path = "/deletekabupaten/{id}")
    public String deletekabupaten(@PathVariable("id") Integer id, Model model){
         model.addAttribute("kabupaten", koneksiJdbc.getKabupatenById(id).get());
         model.addAttribute("provinsi", koneksiJdbc.getProvinsi());
        return "kabupatendetail";
    }
    
    @GetMapping(path= "/listprovinsijson")
    public ResponseEntity<List<Provinsi>> listProvinsiCari(){
        return ResponseEntity.ok().body(koneksiJdbc.getProvinsi());
    }
    
    @GetMapping(path= "/api/listprovinsijson")
    public ResponseEntity<List<Provinsi>> listProvinsiCariJson(){
        return ResponseEntity.ok().body(koneksiJdbc.getProvinsi());
    }
    
    @GetMapping(path= "/api/listprovinsijson/{id}")
    public ResponseEntity<Provinsi> listProvinsiByIdJson(@PathVariable("id") int id ){
        Optional<Provinsi> hasil = koneksiJdbc.getProvinsiById(id);
        if(hasil.isPresent()){
            return ResponseEntity.ok().body(hasil.get());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping(path= "/api/listkabjson/{id}")
    public ResponseEntity<Kabupaten> listKabupatenByIdJson(@PathVariable("id") int id ){
        Optional<Kabupaten> hasil = koneksiJdbc.getKabupatenById(id);
        if(hasil.isPresent()){
            return ResponseEntity.ok().body(hasil.get());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping(path= "/api/listkabjson")
    public ResponseEntity<List<Kabupaten>> listKabupatenCariJson(){
        return ResponseEntity.ok().body(koneksiJdbc.getKabupaten());
    }
    
//    @GetMapping(path= "/listkabjson")
//    public ResponseEntity<List<Kabupaten>> listKabupatenCari(){
//        return ResponseEntity.ok().body(koneksiJdbc.getKabupaten());
//    }
    
    @GetMapping(path= "/listkabjson/{id}")
    public ResponseEntity<List<Kabupaten>> listKabupatenCari(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(koneksiJdbc.getListKabupaten(id));
    }
    
    @GetMapping("/addkecamatan")
    public String createProjectFormKec(Model model){
        model.addAttribute("kecamatan", new Kecamatan());
        model.addAttribute("kabupaten", koneksiJdbc.getKabupaten());
        model.addAttribute("provinsi", koneksiJdbc.getProvinsi());
        return "addkec";
    }
    
    @GetMapping(path = "/listkecamatan")
    public ModelAndView listKecamatan(ModelAndView model){
        Map<String, List<Kecamatan>> map = new HashMap();
        map.put("listKecamatan", koneksiJdbc.getKecamatan());
        return new ModelAndView("listkec", map);
    }
    
    @GetMapping(path = "/kecamatandetail/{id}")
    public String kecamatanDetail(@PathVariable("id") Integer id, Model model){
//        Map<String, Kabupaten> map = new HashMap();
//        map.put("kab", koneksiJdbc.getKabupatenById(id).get());
//        return new ModelAndView("kabupatendetail", map);
         model.addAttribute("kecamatan", koneksiJdbc.getKecamatanById(id).get());
         model.addAttribute("kabupaten", koneksiJdbc.getKabupaten());
         model.addAttribute("provinsi", koneksiJdbc.getProvinsi());
        return "kecamatandetail";
    }
    
    @GetMapping(path = "/listkeccari")
    public ModelAndView listKecamatanSearch(@RequestParam(name = "nama") Optional<String> namaParam){
        Map<String, List<Kecamatan>> map = new HashMap();
        map.put("listKecamatan", koneksiJdbc.getKecamatanSearch(namaParam));
        return new ModelAndView("listkec", map);
    }
    
    @PostMapping("/savekec")
    public String savekec(@Valid @ModelAttribute Kecamatan kecamatan, BindingResult result){
        if(result.hasErrors()){
            return "addkec";
        }
        koneksiJdbc.insertKecamatan(kecamatan);
        return "redirect:listkecamatan";
    }
    
    @PostMapping("/updatekec")
    public String updatekec(@Valid @ModelAttribute Kecamatan kecamatan, BindingResult result){
        if(result.hasErrors()){
            return "kecamatandetail";
        }
        koneksiJdbc.updateKecamatan(kecamatan);
        return "redirect:listkecamatan";
    }
    
    
}