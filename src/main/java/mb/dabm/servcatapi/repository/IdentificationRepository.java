package mb.dabm.servcatapi.repository;

import jakarta.persistence.ConstructorResult;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import mb.dabm.servcatapi.entity.Identification;
import mb.dabm.servcatapi.model.IdentificationDto;
import mb.dabm.servcatapi.projection.IdentificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// import org.springframework.data.repository.CrudRepository;

public interface IdentificationRepository
    extends JpaRepository<Identification, Long> {

    /**
     * Example 1 JPQL (Jakarta Persistence Query Language) - with Record JAVA
     * JPQL suporta apenas um subconjunto do padrão SQL. Se você deseja fazer
     * consultas complexas, dê uma olhada em Native SQL Query.
     * @param pageable
     * @return
     */
    @Query("""
        select new mb.dabm.servcatapi.model.IdentificationDto(
            i.fsc as fsc,
            i.niin as niin,
            i.nsn as nsn,
            i.itemName as itemName,
            i.inc as inc,
            i.tiic as tiic,
            i.rpdmrc as rpdmrc,
            i.fmsn as fmsn,
            i.origem as origem
        )
        from Identification i
        order by i.codGen
        """)
    Page<IdentificationDto> findAllIdentificationDto(Pageable pageable);

    /**
     * Example 2 JPQL (Jakarta Persistence Query Language) - with Record JAVA
     * JPQL suporta apenas um subconjunto do padrão SQL. Se você deseja fazer
     * consultas complexas, dê uma olhada em Native SQL Query.
     * @param inc
     * @param pageable
     * @return
     */
    @Query("""
        SELECT new mb.dabm.servcatapi.model.IdentificationDto(
            i.fsc,
            i.niin,
            i.nsn,
            i.itemName,
            i.inc,
            i.tiic,
            i.rpdmrc,
            i.fmsn,
            i.origem
        )
        FROM Identification i
        WHERE i.inc = :inc
        ORDER BY i.codGen
        """)
    Page<IdentificationDto> findByInc(String inc, Pageable pageable);

    /**
     * Examplo 3 - consulta sem query - Derived Query methods - de acordo com as colunas da tabela do BD
     *
     * @param fsc
     * @param pageable
     * @return
     */
    Page<IdentificationDto> findByFsc(String fsc, Pageable pageable);

    /**
     * Examplo 4 - consulta com query - SQL Native e Projection Interface
     *
     * @param niin
     * @return IdentificationDTO
     */
    @Query(
        value = """
            SELECT /*+ FIRST_ROWS */
                 COD_GEN,
                 FSC,
                 NIIN,
                 NSN,
                 ITEM_NAME,
                 INC,
                 TIIC,
                 RPDMRC,
                 FMSN
            FROM GENERAL
            WHERE NIIN = :niin
            """,
        nativeQuery = true
    )
    IdentificationDTO getByNiin(String niin);

    /**
     * Examplo 5 - consulta Derived Query methods - de acordo com as colunas da tabela do BD
     * Containing: where x.field like %param% (wrapped in %)
     * @url https://www.bezkoder.com/jpa-repository-query/
     * @param niin
     * @param pageable
     * @return
     */
    Page<IdentificationDto> findByNiinContaining(String niin, Pageable pageable);

    /**
     * Examplo 6 - consulta Derived Query methods - de acordo com as colunas da tabela do BD
     * StartingWith: where x.field like param% (with appended %)
     * @url https://www.bezkoder.com/jpa-repository-query/
     * @param niin
     * @param pageable
     * @return
     */
    Page<IdentificationDto> findByNiinStartingWith(String niin, Pageable pageable);

    /**
     * Examplo 6 - consulta Derived Query methods - de acordo com as colunas da tabela do BD
     * EndingWith: where x.field like %param (with prepended %)
     * @url https://www.bezkoder.com/jpa-repository-query/
     * @param niin
     * @param pageable
     * @return
     */
    Page<IdentificationDto> findByNiinEndingWith(String niin, Pageable pageable);
}
