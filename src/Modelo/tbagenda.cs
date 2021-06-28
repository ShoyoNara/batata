using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
namespace Tbagenda
{
    #region Tbagenda
    public class Tbagenda
    {
        #region Member Variables
        protected int _id;
        protected string _nome;
        protected string _email;
        protected string _senha;
        protected string _telefone;
        protected string _recado;
        #endregion
        #region Constructors
        public Tbagenda() { }
        public Tbagenda(string nome, string email, string senha, string telefone, string recado)
        {
            this._nome=nome;
            this._email=email;
            this._senha=senha;
            this._telefone=telefone;
            this._recado=recado;
        }
        #endregion
        #region Public Properties
        public virtual int Id
        {
            get {return _id;}
            set {_id=value;}
        }
        public virtual string Nome
        {
            get {return _nome;}
            set {_nome=value;}
        }
        public virtual string Email
        {
            get {return _email;}
            set {_email=value;}
        }
        public virtual string Senha
        {
            get {return _senha;}
            set {_senha=value;}
        }
        public virtual string Telefone
        {
            get {return _telefone;}
            set {_telefone=value;}
        }
        public virtual string Recado
        {
            get {return _recado;}
            set {_recado=value;}
        }
        #endregion
    }
    #endregion
}