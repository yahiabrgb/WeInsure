package tn.weinsure1.repository;

import java.util.List;   
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.weinsure1.entities.ContractType;
import tn.weinsure1.entities.sinister;
import tn.weinsure1.entities.sinisterstatus;
 
public interface sinisterRepository  extends CrudRepository<sinister, Long> {
	@Query("SELECT s From sinister s WHERE s.status =:status")
	List<sinister> findSinisterByStatus(@Param("status")  sinisterstatus sinstatus );
	@Query("select c from sinister c where c.status = 'enAttente' ")
    List<sinister> findSinisterByStatusEnAttente(); 
	@Query("select c from sinister c where c.status = 'rejected' ")
    List<sinister> findSinisterByStatusRejected();
	@Query("SELECT s From sinister s WHERE s.description like %:description%")
	List<sinister> findSinisterByStatuss(@Param("description")  String description ); 
    @Query("select c from sinister c where c.description like  %?1% ORDER By description ASC ")
	List<sinister> findByDescription(String desc); 	
	@Query("select c from sinister c where cast (c.dateOccurence as string) like %:date%")
    List<sinister> findByYear(@Param("date") String date);
	@Query("select c from sinister c where CONCAT (c.status ,c.description, c.dateOccurence )like  %?1% ")
	List<sinister> findByAny(String any); 
	@Query("select c from sinister c where c.user.id =:idd")
	List<sinister> findbyuserid(@Param("idd") Long id);
	@Query("select c.Duration from Contract c   where  c.idcontract =:idd And c.Type =:str ")
	int findcontractdurationBysinister(@Param("idd") Long id2 , @Param("str") ContractType str);
	@Query("select c from sinister c  JOIN c.user u Join u.contraint s where s.idcontraint =:idd AND c.status = 'WaitingForReclamation' ")
	List<sinister> findSinisterDescriptionwithUR(@Param("idd") Long id);
	@Query("select AVG(u.salary) from sinister s  JOIN  s.user u JOIN u.contracts c where c.Type = 'VieEntiere' AND s.typeSinistre = 'VieEntiere' ")
	float GetSalaireMoyenUsersContractSinister() ;
	@Query("select AVG(c.Price) from sinister s  JOIN  s.user u JOIN u.contracts c where c.Type = 'VieEntiere' AND s.typeSinistre = 'VieEntiere' ")
	float GetPrixContratContractSinister() ;
	@Query("select c.idcontract from Contract c   JOIN c.user u Join u.sinisterList l where l.idSinistre =:idd AND l.typeSinistre ='VieEntiere' AND c.Type = 'VieEntiere'")
	Long findcontractidbysisnVIEENTIERE(@Param("idd") Long id2 );
	@Query("select c.idcontract from Contract c   JOIN c.user u Join u.sinisterList l where l.idSinistre =:idd AND l.typeSinistre ='TemporairedecesEmprunteur' AND c.Type = 'TemporairedecesEmprunteur'")
	Long findcontractidbysisnTDEMPREUNTEUR(@Param("idd") Long id2 );
	@Query("select c.idcontract from Contract c   JOIN c.user u Join u.sinisterList l where l.idSinistre =:idd AND l.typeSinistre ='casDeces' AND c.Type = 'casDeces'")
	Long findcontractidbysisnCASDECES(@Param("idd") Long id2 );
	@Query("select c.idcontract from Contract c   JOIN c.user u Join u.sinisterList l where l.idSinistre =:idd AND l.typeSinistre ='casDecesperiodique' AND c.Type = 'casDecesperiodique'")
	Long findcontractidbysisnCASDECESP(@Param("idd") Long id2 );
	@Query("select c from sinister c JOIN c.user u JOIN u.contracts s WHERE s.idcontract IS NOT NULL  ")
    List<sinister> findSinisterWithContracts();


	
	
	
	
}