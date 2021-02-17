package com.cfg.demo.core.website;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public  interface  WebSiteRepo   extends JpaRepository<WebSite, Long>{
}
