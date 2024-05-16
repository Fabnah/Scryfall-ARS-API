
package project.scryfall.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.scryfall.Entities.CardArs;

@Repository
public interface IcardArsRepository extends JpaRepository<CardArs, Long>{
    CardArs findByNameArs(String nameArs);
}

