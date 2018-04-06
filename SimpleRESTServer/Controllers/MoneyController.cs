using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
namespace SimpleRESTServer.Controllers
{
    public class MoneyController : ApiController
    {
        // GET: api/Money
        public List<Money> GETList()
        {
            DBMoneyDataContext db = new DBMoneyDataContext();
            return db.Moneys.ToList();
        }

        public Money GET(int id)
        {
            DBMoneyDataContext db = new DBMoneyDataContext();
            return db.Moneys.FirstOrDefault(x => x.id == id);
        }
        // POST: api/Money
        public bool POST(string content, string date, long money1, string category)
        {
            try
            {
                DBMoneyDataContext db = new DBMoneyDataContext();
                Money m = new Money();
                m.content = content;
                m.date = date;
                m.money1 = money1;
                m.category = category;
                db.Moneys.InsertOnSubmit(m);
                db.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        // PUT: api/Money
        public bool PUT(int id, string content, string date, long money1, string category)
        {
            try
            {
                DBMoneyDataContext db = new DBMoneyDataContext();
                //lấy money tồn tại ra
                Money m = db.Moneys.FirstOrDefault(x =>x.id==id);
                if (m == null) return false;//không tồn tại false
                m.content = content;
                m.date = date;
                m.money1 = money1;
                m.category = category;
                db.SubmitChanges();//xác nhận chỉnh sửa
                return true;
            }
            catch
            {
                return false;
            }
        }

        // DELETE: api/Money
        public bool DELETE(int id)
        {
            DBMoneyDataContext db = new DBMoneyDataContext();
            //lấy food tồn tại ra
            Money m = db.Moneys.FirstOrDefault(x => x.id == id);
            if (m == null) return false;
            db.Moneys.DeleteOnSubmit(m);
            db.SubmitChanges();
            return true;
        }
    }
}
