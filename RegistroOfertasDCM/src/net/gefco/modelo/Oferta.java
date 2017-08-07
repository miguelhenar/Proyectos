package net.gefco.modelo;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.gefco.util.CfgUtil;

@Entity
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ofer_codigo;
	private Date ofer_fechaRegistro;
	private String ofer_empresa;
	private Boolean ofer_esAgente;
	private Boolean ofer_esCliente;
	private Boolean ofer_esProspect;	
	@ManyToOne
	@JoinColumn(name = "ofer_pole", referencedColumnName = "pole_codigo")
	private Pole ofer_pole = new Pole();
	private String ofer_tipoEmpresa;
	@ManyToOne
	@JoinColumn(name = "ofer_usuario", referencedColumnName = "usua_codigo")
	private Usuario ofer_usuario = new Usuario();
	private String ofer_descripcionServicio;
	@ManyToOne
	@JoinColumn(name = "ofer_fuente", referencedColumnName = "fuen_codigo")
	private Fuente ofer_fuente = new Fuente();
	@ManyToOne
	@JoinColumn(name = "ofer_tipoOferta", referencedColumnName = "tiof_codigo")
	private TipoOferta ofer_tipoOferta = new TipoOferta();
	private Date ofer_fechaTope;
	@ManyToOne
	@JoinColumn(name = "ofer_preparacionOferta", referencedColumnName = "prof_codigo")
	private PreparacionOferta ofer_preparacionOferta = new PreparacionOferta();
	@ManyToOne
	@JoinColumn(name = "ofer_maduracion", referencedColumnName = "madu_codigo")
	private Maduracion ofer_maduracion = new Maduracion();
	private Date ofer_fechaUltimoEstado;
	@ManyToOne
	@JoinColumn(name = "ofer_motivoPerdida", referencedColumnName = "mope_codigo")
	private MotivoPerdida ofer_motivoPerdida = new MotivoPerdida();
	private Boolean ofer_tlaTransporte;
	private Boolean ofer_tlaPvno;
	private Boolean ofer_ovlGrupaje;
	private Boolean ofer_ovlLtl;
	private Boolean ofer_ovlFtl;	
	private Boolean ofer_ovlXdock;
	private Boolean ofer_ovlMaf;
	private Boolean ofer_iliAlmacenaje;
	private Boolean ofer_iliTransporte;
	private Boolean ofer_gsmTransporte;
	private Boolean ofer_ovsGrupaje;
	private Boolean ofer_ovsLcl;
	private Boolean ofer_ovsFcl;
	private Boolean ofer_ovsFtl;
	private Boolean ofer_ovsAereo;
	private Boolean ofer_nacional;
	private Boolean ofer_internacional;
	private Boolean ofer_campanyaMarketing;
	private Boolean ofer_gefcoSpecial;
	private Double ofer_cotizadoOvl;
	private Double ofer_cotizadoOvs;
	private Double ofer_cotizadoIli;
	private Double ofer_cotizadoTla;
	private Double ofer_cotizadoGsm;
	private Double ofer_conseguidoOvl;
	private Double ofer_conseguidoOvs;
	private Double ofer_conseguidoIli;
	private Double ofer_conseguidoTla;
	private Double ofer_conseguidoGsm;	
	private Double ofer_renovadoOvl;
	private Double ofer_renovadoOvs;
	private Double ofer_renovadoIli;
	private Double ofer_renovadoTla;
	private Double ofer_renovadoGsm;
	private String ofer_oportunidadTogether;
	
	@OneToMany(mappedBy="ofer_codigo",fetch=FetchType.LAZY)
	private Set<HistoricoOferta> historicoOferta;
	
	public Oferta() {
		super();
	}
	
	public Oferta(int ofer_codigo, Date ofer_fechaRegistro,
			String ofer_empresa, Boolean ofer_esAgente, Boolean ofer_esCliente,
			Boolean ofer_esProspect, Pole ofer_pole, String ofer_tipoEmpresa,
			Usuario ofer_usuario, String ofer_descripcionServicio,
			Fuente ofer_fuente, TipoOferta ofer_tipoOferta,
			Date ofer_fechaTope, PreparacionOferta ofer_preparacionOferta,
			Maduracion ofer_maduracion, Date ofer_fechaUltimoEstado,
			MotivoPerdida ofer_motivoPerdida, Boolean ofer_tlaTransporte,
			Boolean ofer_tlaPvno, Boolean ofer_ovlGrupaje, Boolean ofer_ovlLtl,
			Boolean ofer_ovlFtl, Boolean ofer_ovlXdock, Boolean ofer_ovlMaf,
			Boolean ofer_iliAlmacenaje, Boolean ofer_iliTransporte,
			Boolean ofer_gsmTransporte, Boolean ofer_ovsGrupaje,
			Boolean ofer_ovsLcl, Boolean ofer_ovsFcl, Boolean ofer_ovsFtl,
			Boolean ofer_ovsAereo, Boolean ofer_nacional,
			Boolean ofer_internacional, Boolean ofer_campanyaMarketing,
			Boolean ofer_gefcoSpecial, Double ofer_cotizadoOvl,
			Double ofer_cotizadoOvs, Double ofer_cotizadoIli,
			Double ofer_cotizadoTla, Double ofer_cotizadoGsm,
			Double ofer_conseguidoOvl, Double ofer_conseguidoOvs,
			Double ofer_conseguidoIli, Double ofer_conseguidoTla,
			Double ofer_conseguidoGsm, Double ofer_renovadoOvl,
			Double ofer_renovadoOvs, Double ofer_renovadoIli,
			Double ofer_renovadoTla, Double ofer_renovadoGsm,
			String ofer_oportunidadTogether,
			Set<HistoricoOferta> historicoOferta) {
		super();
		this.ofer_codigo = ofer_codigo;
		this.ofer_fechaRegistro = ofer_fechaRegistro;
		this.ofer_empresa = ofer_empresa;
		this.ofer_esAgente = ofer_esAgente;
		this.ofer_esCliente = ofer_esCliente;
		this.ofer_esProspect = ofer_esProspect;
		this.ofer_pole = ofer_pole;
		this.ofer_tipoEmpresa = ofer_tipoEmpresa;
		this.ofer_usuario = ofer_usuario;
		this.ofer_descripcionServicio = ofer_descripcionServicio;
		this.ofer_fuente = ofer_fuente;
		this.ofer_tipoOferta = ofer_tipoOferta;
		this.ofer_fechaTope = ofer_fechaTope;
		this.ofer_preparacionOferta = ofer_preparacionOferta;
		this.ofer_maduracion = ofer_maduracion;
		this.ofer_fechaUltimoEstado = ofer_fechaUltimoEstado;
		this.ofer_motivoPerdida = ofer_motivoPerdida;
		this.ofer_tlaTransporte = ofer_tlaTransporte;
		this.ofer_tlaPvno = ofer_tlaPvno;
		this.ofer_ovlGrupaje = ofer_ovlGrupaje;
		this.ofer_ovlLtl = ofer_ovlLtl;
		this.ofer_ovlFtl = ofer_ovlFtl;
		this.ofer_ovlXdock = ofer_ovlXdock;
		this.ofer_ovlMaf = ofer_ovlMaf;
		this.ofer_iliAlmacenaje = ofer_iliAlmacenaje;
		this.ofer_iliTransporte = ofer_iliTransporte;
		this.ofer_gsmTransporte = ofer_gsmTransporte;
		this.ofer_ovsGrupaje = ofer_ovsGrupaje;
		this.ofer_ovsLcl = ofer_ovsLcl;
		this.ofer_ovsFcl = ofer_ovsFcl;
		this.ofer_ovsFtl = ofer_ovsFtl;
		this.ofer_ovsAereo = ofer_ovsAereo;
		this.ofer_nacional = ofer_nacional;
		this.ofer_internacional = ofer_internacional;
		this.ofer_campanyaMarketing = ofer_campanyaMarketing;
		this.ofer_gefcoSpecial = ofer_gefcoSpecial;
		this.ofer_cotizadoOvl = ofer_cotizadoOvl;
		this.ofer_cotizadoOvs = ofer_cotizadoOvs;
		this.ofer_cotizadoIli = ofer_cotizadoIli;
		this.ofer_cotizadoTla = ofer_cotizadoTla;
		this.ofer_cotizadoGsm = ofer_cotizadoGsm;
		this.ofer_conseguidoOvl = ofer_conseguidoOvl;
		this.ofer_conseguidoOvs = ofer_conseguidoOvs;
		this.ofer_conseguidoIli = ofer_conseguidoIli;
		this.ofer_conseguidoTla = ofer_conseguidoTla;
		this.ofer_conseguidoGsm = ofer_conseguidoGsm;
		this.ofer_renovadoOvl = ofer_renovadoOvl;
		this.ofer_renovadoOvs = ofer_renovadoOvs;
		this.ofer_renovadoIli = ofer_renovadoIli;
		this.ofer_renovadoTla = ofer_renovadoTla;
		this.ofer_renovadoGsm = ofer_renovadoGsm;
		this.ofer_oportunidadTogether = ofer_oportunidadTogether;
		this.historicoOferta = historicoOferta;
	}

	public int getOfer_codigo() {
		return ofer_codigo;
	}
	public void setOfer_codigo(int oferCodigo) {
		ofer_codigo = oferCodigo;
	}	
	public Date getOfer_fechaRegistro() {
		return ofer_fechaRegistro;
	}
	public void setOfer_fechaRegistro(Date oferFechaRegistro) {
		ofer_fechaRegistro = oferFechaRegistro;
	}
	public String getOfer_empresa() {
		return ofer_empresa;
	}
	public void setOfer_empresa(String oferEmpresa) {
		ofer_empresa = oferEmpresa;
	}	
	public Boolean getOfer_esAgente() {
		return ofer_esAgente;
	}
	public void setOfer_esAgente(Boolean oferEsAgente) {
		ofer_esAgente = oferEsAgente;
	}
	public Boolean getOfer_esCliente() {
		return ofer_esCliente;
	}
	public void setOfer_esCliente(Boolean oferEsCliente) {
		ofer_esCliente = oferEsCliente;
	}
	public Boolean getOfer_esProspect() {
		return ofer_esProspect;
	}
	public void setOfer_esProspect(Boolean oferEsProspect) {
		ofer_esProspect = oferEsProspect;
	}
	public Pole getOfer_pole() {
		return ofer_pole;
	}
	public void setOfer_pole(Pole oferPole) {
		ofer_pole = oferPole;
	}
	public String getOfer_tipoEmpresa() {
		return ofer_tipoEmpresa;
	}
	public void setOfer_tipoEmpresa(String oferTipoEmpresa) {
		ofer_tipoEmpresa = oferTipoEmpresa;
	}
	public Usuario getOfer_usuario() {
		return ofer_usuario;
	}
	public void setOfer_usuario(Usuario oferUsuario) {
		ofer_usuario = oferUsuario;
	}
	public String getOfer_descripcionServicio() {
		return ofer_descripcionServicio;
	}
	public void setOfer_descripcionServicio(String oferDescripcionServicio) {
		ofer_descripcionServicio = oferDescripcionServicio;
	}
	public Fuente getOfer_fuente() {
		return ofer_fuente;
	}
	public void setOfer_fuente(Fuente oferFuente) {
		ofer_fuente = oferFuente;
	}
	public TipoOferta getOfer_tipoOferta() {
		return ofer_tipoOferta;
	}
	public void setOfer_tipoOferta(TipoOferta oferTipoOferta) {
		ofer_tipoOferta = oferTipoOferta;
	}
	public Date getOfer_fechaTope() {
		return ofer_fechaTope;
	}
	public void setOfer_fechaTope(Date oferFechaTope) {
		ofer_fechaTope = oferFechaTope;
	}
	public PreparacionOferta getOfer_preparacionOferta() {
		return ofer_preparacionOferta;
	}
	public void setOfer_preparacionOferta(PreparacionOferta oferPreparacionOferta) {
		ofer_preparacionOferta = oferPreparacionOferta;
	}
	public Maduracion getOfer_maduracion() {
		return ofer_maduracion;
	}
	public void setOfer_maduracion(Maduracion oferMaduracion) {
		ofer_maduracion = oferMaduracion;
	}
	public Date getOfer_fechaUltimoEstado() {
		return ofer_fechaUltimoEstado;
	}
	public void setOfer_fechaUltimoEstado(Date oferFechaUltimoEstado) {
		ofer_fechaUltimoEstado = oferFechaUltimoEstado;
	}
	public MotivoPerdida getOfer_motivoPerdida() {
		return ofer_motivoPerdida;
	}
	public void setOfer_motivoPerdida(MotivoPerdida oferMotivoPerdida) {
		ofer_motivoPerdida = oferMotivoPerdida;
	}
	public Boolean getOfer_tlaTransporte() {
		return ofer_tlaTransporte;
	}
	public void setOfer_tlaTransporte(Boolean oferTlaTransporte) {
		ofer_tlaTransporte = oferTlaTransporte;
	}
	public Boolean getOfer_tlaPvno() {
		return ofer_tlaPvno;
	}
	public void setOfer_tlaPvno(Boolean oferTlaPvno) {
		ofer_tlaPvno = oferTlaPvno;
	}
	public Boolean getOfer_ovlGrupaje() {
		return ofer_ovlGrupaje;
	}
	public void setOfer_ovlGrupaje(Boolean oferOvlGrupaje) {
		ofer_ovlGrupaje = oferOvlGrupaje;
	}
	public Boolean getOfer_ovlLtl() {
		return ofer_ovlLtl;
	}
	public void setOfer_ovlLtl(Boolean oferOvlLtl) {
		ofer_ovlLtl = oferOvlLtl;
	}
	public Boolean getOfer_ovlFtl() {
		return ofer_ovlFtl;
	}
	public void setOfer_ovlFtl(Boolean oferOvlFtl) {
		ofer_ovlFtl = oferOvlFtl;
	}
	public Boolean getOfer_ovlXdock() {
		return ofer_ovlXdock;
	}
	public void setOfer_ovlXdock(Boolean oferOvlXdock) {
		ofer_ovlXdock = oferOvlXdock;
	}
	public Boolean getOfer_ovlMaf() {
		return ofer_ovlMaf;
	}
	public void setOfer_ovlMaf(Boolean oferOvlMaf) {
		ofer_ovlMaf = oferOvlMaf;
	}
	public Boolean getOfer_iliAlmacenaje() {
		return ofer_iliAlmacenaje;
	}
	public void setOfer_iliAlmacenaje(Boolean oferIliAlmacenaje) {
		ofer_iliAlmacenaje = oferIliAlmacenaje;
	}
	public Boolean getOfer_iliTransporte() {
		return ofer_iliTransporte;
	}
	public void setOfer_iliTransporte(Boolean oferIliTransporte) {
		ofer_iliTransporte = oferIliTransporte;
	}
	public Boolean getOfer_gsmTransporte() {
		return ofer_gsmTransporte;
	}
	public void setOfer_gsmTransporte(Boolean oferGsmTransporte) {
		ofer_gsmTransporte = oferGsmTransporte;
	}
	public Boolean getOfer_ovsGrupaje() {
		return ofer_ovsGrupaje;
	}
	public void setOfer_ovsGrupaje(Boolean oferOvsGrupaje) {
		ofer_ovsGrupaje = oferOvsGrupaje;
	}
	public Boolean getOfer_ovsLcl() {
		return ofer_ovsLcl;
	}
	public void setOfer_ovsLcl(Boolean oferOvsLcl) {
		ofer_ovsLcl = oferOvsLcl;
	}
	public Boolean getOfer_ovsFcl() {
		return ofer_ovsFcl;
	}
	public void setOfer_ovsFcl(Boolean oferOvsFcl) {
		ofer_ovsFcl = oferOvsFcl;
	}
	public Boolean getOfer_ovsFtl() {
		return ofer_ovsFtl;
	}
	public void setOfer_ovsFtl(Boolean oferOvsFtl) {
		ofer_ovsFtl = oferOvsFtl;
	}
	public Boolean getOfer_ovsAereo() {
		return ofer_ovsAereo;
	}
	public void setOfer_ovsAereo(Boolean oferOvsAereo) {
		ofer_ovsAereo = oferOvsAereo;
	}
	public Boolean getOfer_nacional() {
		return ofer_nacional;
	}
	public void setOfer_nacional(Boolean oferNacional) {
		ofer_nacional = oferNacional;
	}
	public Boolean getOfer_internacional() {
		return ofer_internacional;
	}
	public void setOfer_internacional(Boolean oferInternacional) {
		ofer_internacional = oferInternacional;
	}
	public Boolean getOfer_campanyaMarketing() {
		return ofer_campanyaMarketing;
	}
	public void setOfer_campanyaMarketing(Boolean oferCampanyaMarketing) {
		ofer_campanyaMarketing = oferCampanyaMarketing;
	}	
	public Boolean getOfer_gefcoSpecial() {
		return ofer_gefcoSpecial;
	}
	public void setOfer_gefcoSpecial(Boolean oferGefcoSpecial) {
		ofer_gefcoSpecial = oferGefcoSpecial;
	}
	public Double getOfer_cotizadoOvl() {
		return ofer_cotizadoOvl;
	}
	public void setOfer_cotizadoOvl(Double oferCotizadoOvl) {
		ofer_cotizadoOvl = oferCotizadoOvl;
	}
	public Double getOfer_cotizadoOvs() {
		return ofer_cotizadoOvs;
	}
	public void setOfer_cotizadoOvs(Double oferCotizadoOvs) {
		ofer_cotizadoOvs = oferCotizadoOvs;
	}
	public Double getOfer_cotizadoIli() {
		return ofer_cotizadoIli;
	}
	public void setOfer_cotizadoIli(Double oferCotizadoIli) {
		ofer_cotizadoIli = oferCotizadoIli;
	}
	public Double getOfer_cotizadoTla() {
		return ofer_cotizadoTla;
	}
	public void setOfer_cotizadoTla(Double oferCotizadoTla) {
		ofer_cotizadoTla = oferCotizadoTla;
	}
	public Double getOfer_cotizadoGsm() {
		return ofer_cotizadoGsm;
	}
	public void setOfer_cotizadoGsm(Double oferCotizadoGsm) {
		ofer_cotizadoGsm = oferCotizadoGsm;
	}
	public Double getOfer_conseguidoOvl() {
		return ofer_conseguidoOvl;
	}
	public void setOfer_conseguidoOvl(Double oferConseguidoOvl) {
		ofer_conseguidoOvl = oferConseguidoOvl;
	}
	public Double getOfer_conseguidoOvs() {
		return ofer_conseguidoOvs;
	}
	public void setOfer_conseguidoOvs(Double oferConseguidoOvs) {
		ofer_conseguidoOvs = oferConseguidoOvs;
	}
	public Double getOfer_conseguidoIli() {
		return ofer_conseguidoIli;
	}
	public void setOfer_conseguidoIli(Double oferConseguidoIli) {
		ofer_conseguidoIli = oferConseguidoIli;
	}
	public Double getOfer_conseguidoTla() {
		return ofer_conseguidoTla;
	}
	public void setOfer_conseguidoTla(Double oferConseguidoTla) {
		ofer_conseguidoTla = oferConseguidoTla;
	}
	public Double getOfer_conseguidoGsm() {
		return ofer_conseguidoGsm;
	}
	public void setOfer_conseguidoGsm(Double oferConseguidoGsm) {
		ofer_conseguidoGsm = oferConseguidoGsm;
	}
			
	public Double getOfer_renovadoOvl() {
		return ofer_renovadoOvl;
	}
	public void setOfer_renovadoOvl(Double oferRenovadoOvl) {
		ofer_renovadoOvl = oferRenovadoOvl;
	}
	public Double getOfer_renovadoOvs() {
		return ofer_renovadoOvs;
	}
	public void setOfer_renovadoOvs(Double oferRenovadoOvs) {
		ofer_renovadoOvs = oferRenovadoOvs;
	}
	public Double getOfer_renovadoIli() {
		return ofer_renovadoIli;
	}
	public void setOfer_renovadoIli(Double oferRenovadoIli) {
		ofer_renovadoIli = oferRenovadoIli;
	}
	public Double getOfer_renovadoTla() {
		return ofer_renovadoTla;
	}
	public void setOfer_renovadoTla(Double oferRenovadoTla) {
		ofer_renovadoTla = oferRenovadoTla;
	}
	public Double getOfer_renovadoGsm() {
		return ofer_renovadoGsm;
	}
	public void setOfer_renovadoGsm(Double oferRenovadoGsm) {
		ofer_renovadoGsm = oferRenovadoGsm;
	}
	public String getOfer_oportunidadTogether() {
		return ofer_oportunidadTogether;
	}
	public void setOfer_oportunidadTogether(String ofer_oportunidadTogether) {
		this.ofer_oportunidadTogether = ofer_oportunidadTogether;
	}
	
	public void setHistoricoOferta(Set<HistoricoOferta> historicoOferta) {
		this.historicoOferta = historicoOferta;
	}
	public Set<HistoricoOferta> getHistoricoOferta() {
		return historicoOferta;
	}
	
	public String getOfer_fechaTopeConFormato() {
		return (ofer_fechaTope==null?"":CfgUtil.SDF_Barras.format(ofer_fechaTope));
	}
	
	public String getOfer_fechaUltimoEstadoConFormato() {
		return (ofer_fechaUltimoEstado==null?"":CfgUtil.SDF_Barras.format(ofer_fechaUltimoEstado));
	}
	
	public void copiarValores(Oferta dAux){
		ofer_codigo = dAux.ofer_codigo;
		ofer_fechaRegistro = dAux.ofer_fechaRegistro;
		ofer_empresa = dAux.ofer_empresa;
		ofer_esAgente = dAux.ofer_esAgente;
		ofer_esCliente = dAux.ofer_esCliente;
		ofer_esProspect = dAux.ofer_esProspect;
		ofer_pole = dAux.ofer_pole;
		ofer_tipoEmpresa = dAux.ofer_tipoEmpresa;
		ofer_usuario = dAux.ofer_usuario;
		ofer_descripcionServicio = dAux.ofer_descripcionServicio;
		ofer_fuente = dAux.ofer_fuente;
		ofer_tipoOferta = dAux.ofer_tipoOferta;
		ofer_fechaTope = dAux.ofer_fechaTope;
		ofer_preparacionOferta = dAux.ofer_preparacionOferta;
		ofer_maduracion = dAux.ofer_maduracion;
		ofer_fechaUltimoEstado = dAux.ofer_fechaUltimoEstado;
		ofer_motivoPerdida = dAux.ofer_motivoPerdida;
		ofer_tlaTransporte = dAux.ofer_tlaTransporte;
		ofer_tlaPvno = dAux.ofer_tlaPvno;
		ofer_ovlGrupaje = dAux.ofer_ovlGrupaje;
		ofer_ovlLtl = dAux.ofer_ovlLtl;
		ofer_ovlFtl = dAux.ofer_ovlFtl;
		ofer_ovlXdock = dAux.ofer_ovlXdock;
		ofer_ovlMaf = dAux.ofer_ovlMaf;
		ofer_iliAlmacenaje = dAux.ofer_iliAlmacenaje;
		ofer_iliTransporte = dAux.ofer_iliTransporte;
		ofer_gsmTransporte = dAux.ofer_gsmTransporte;
		ofer_ovsGrupaje = dAux.ofer_ovsGrupaje;
		ofer_ovsLcl = dAux.ofer_ovsLcl;
		ofer_ovsFcl = dAux.ofer_ovsFcl;
		ofer_ovsFtl = dAux.ofer_ovsFtl;
		ofer_ovsAereo = dAux.ofer_ovsAereo;
		ofer_nacional = dAux.ofer_nacional;
		ofer_internacional = dAux.ofer_internacional;
		ofer_campanyaMarketing = dAux.ofer_campanyaMarketing;
		ofer_gefcoSpecial = dAux.ofer_gefcoSpecial;
		ofer_cotizadoOvl = dAux.ofer_cotizadoOvl==0.0 ? null : dAux.ofer_cotizadoOvl;
		ofer_cotizadoOvs = dAux.ofer_cotizadoOvs==0.0 ? null : dAux.ofer_cotizadoOvs;
		ofer_cotizadoIli = dAux.ofer_cotizadoIli==0.0 ? null : dAux.ofer_cotizadoIli;
		ofer_cotizadoTla = dAux.ofer_cotizadoTla==0.0 ? null : dAux.ofer_cotizadoTla;
		ofer_cotizadoGsm = dAux.ofer_cotizadoGsm==0.0 ? null : dAux.ofer_cotizadoGsm;
		ofer_conseguidoOvl = dAux.ofer_conseguidoOvl==0.0 ? null : dAux.ofer_conseguidoOvl;
		ofer_conseguidoOvs = dAux.ofer_conseguidoOvs==0.0 ? null : dAux.ofer_conseguidoOvs;
		ofer_conseguidoIli = dAux.ofer_conseguidoIli==0.0 ? null : dAux.ofer_conseguidoIli;
		ofer_conseguidoTla = dAux.ofer_conseguidoTla==0.0 ? null : dAux.ofer_conseguidoTla;
		ofer_conseguidoGsm = dAux.ofer_conseguidoGsm==0.0 ? null : dAux.ofer_conseguidoGsm;
		ofer_renovadoOvl = dAux.ofer_renovadoOvl==0.0 ? null : dAux.ofer_renovadoOvl;
		ofer_renovadoOvs = dAux.ofer_renovadoOvs==0.0 ? null : dAux.ofer_renovadoOvs;
		ofer_renovadoIli = dAux.ofer_renovadoIli==0.0 ? null : dAux.ofer_renovadoIli;
		ofer_renovadoTla = dAux.ofer_renovadoTla==0.0 ? null : dAux.ofer_renovadoTla;
		ofer_renovadoGsm = dAux.ofer_renovadoGsm==0.0 ? null : dAux.ofer_renovadoGsm;
		ofer_oportunidadTogether = dAux.ofer_oportunidadTogether;
		historicoOferta= dAux.historicoOferta;
	}
	
	public void vaciar() {
		ofer_codigo=0;
		ofer_fechaRegistro =  new Date();
		ofer_empresa = null;
		ofer_esAgente = null;
		ofer_esCliente = null;
		ofer_esProspect = null;	
		ofer_pole = new Pole();
		ofer_tipoEmpresa = null;
		ofer_usuario = new Usuario();
		ofer_descripcionServicio = null;
		ofer_fuente = new Fuente();
		ofer_tipoOferta = new TipoOferta();
		ofer_fechaTope = null;
		ofer_preparacionOferta = new PreparacionOferta();
		ofer_maduracion = new Maduracion();
		ofer_fechaUltimoEstado = new Date();
		ofer_motivoPerdida = new MotivoPerdida();
		ofer_tlaTransporte = null;
		ofer_tlaPvno = null;
		ofer_ovlGrupaje = null;
		ofer_ovlLtl = null;
		ofer_ovlFtl = null;	
		ofer_ovlXdock = null;
		ofer_ovlMaf = null;
		ofer_iliAlmacenaje = null;
		ofer_iliTransporte = null;
		ofer_gsmTransporte = null;
		ofer_ovsGrupaje = null;
		ofer_ovsLcl = null;
		ofer_ovsFcl = null;
		ofer_ovsFtl = null;
		ofer_ovsAereo = null;
		ofer_nacional = null;
		ofer_internacional = null;
		ofer_campanyaMarketing = null;
		ofer_gefcoSpecial = null;
		ofer_cotizadoOvl = null;
		ofer_cotizadoOvs = null;
		ofer_cotizadoIli = null;
		ofer_cotizadoTla = null;
		ofer_cotizadoGsm = null;
		ofer_conseguidoOvl = null;
		ofer_conseguidoOvs = null;
		ofer_conseguidoIli = null;
		ofer_conseguidoTla = null;
		ofer_conseguidoGsm = null;	
		ofer_renovadoOvl = null;
		ofer_renovadoOvs = null;
		ofer_renovadoIli = null;
		ofer_renovadoTla = null;
		ofer_renovadoGsm = null;
		ofer_oportunidadTogether = null;
		historicoOferta= null; 
	}
	
}
